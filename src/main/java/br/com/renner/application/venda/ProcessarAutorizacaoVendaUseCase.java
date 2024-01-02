package br.com.renner.application.venda;

import br.com.renner.application.UseCase;
import br.com.renner.application.mapper.AutorizacaoVendaInputMapper;
import br.com.renner.domain.enumerators.MensagensNegociosEnum;
import br.com.renner.domain.exception.ExternalAPICallerFeignException;
import br.com.renner.domain.models.DadosConfirmacaoCanal;
import br.com.renner.domain.models.MatrizTributaria;
import br.com.renner.domain.models.sefaz.DadosConfirmacaoSefaz;
import br.com.renner.domain.models.sefaz.DadosVenda;
import br.com.renner.domain.models.sefaz.Product;
import br.com.renner.domain.models.venda.AutorizacaoVenda;
import br.com.renner.domain.ports.output.BuscarMatrizTributariaRestOutPort;
import br.com.renner.domain.ports.output.EnviarDadosVendaRestOutPort;
import br.com.renner.domain.ports.output.SalvarDadosVendaPersistenceOutPort;
import br.com.renner.infrastructure.adapters.input.rest.dtos.AutorizacaoVendaDTO;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;

public class ProcessarAutorizacaoVendaUseCase extends UseCase<ProcessarAutorizacaoVendaUseCase.Input> {

    private final AutorizacaoVendaInputMapper mapper;
    private final BuscarMatrizTributariaRestOutPort matrizTributariaOutPort;
    private final EnviarDadosVendaRestOutPort enviarDadosVendaRestOutPort;
    private final SalvarDadosVendaPersistenceOutPort salvarDadosVendaPersistenceOutPort;

    public ProcessarAutorizacaoVendaUseCase(final AutorizacaoVendaInputMapper mapper,
                                            final BuscarMatrizTributariaRestOutPort matrizTributariaOutPort,
                                            final EnviarDadosVendaRestOutPort enviarDadosVendaRestOutPort,
                                            final SalvarDadosVendaPersistenceOutPort salvarDadosVendaPersistenceOutPort) {
        this.mapper = Objects.requireNonNull(mapper);
        this.matrizTributariaOutPort = Objects.requireNonNull(matrizTributariaOutPort);
        this.enviarDadosVendaRestOutPort = Objects.requireNonNull(enviarDadosVendaRestOutPort);
        this.salvarDadosVendaPersistenceOutPort = Objects.requireNonNull(salvarDadosVendaPersistenceOutPort);
    }

    @Override
    public void execute(Input input) {

        AutorizacaoVenda autorizacaoVenda = mapper.toDomain(input.dto());

        DadosConfirmacaoCanal dadosConfirmacaoCanal = null;
        String motivo = null;

        try {
            List<Product> products = new ArrayList<>();
            input.dto.getItens().stream().forEach(item -> {
                MatrizTributaria matrizTributaria = matrizTributariaOutPort.buscarTributo(item.getSku());

                Product product = Product.builder()
                        .sku(item.getSku())
                        .amount(item.getQuantidade())
                        .value(item.getValor())
                        .icmsValue(matrizTributaria.getValorIcms())
                        .pisValue(matrizTributaria.getValorPis())
                        .difaulValue(matrizTributaria.getValorDifaul())
                        .fcpIcmsValue(matrizTributaria.getValorFcpIcms()).build();

                products.add(product);
            });

            DadosVenda dadosVenda = mapper.toDadosVendaDomain(input.dto());
            dadosVenda.setProducts(products);
            DadosConfirmacaoSefaz dadosConfirmacaoSefaz = enviarDadosVendaRestOutPort.enviarDadosParaSefaz(dadosVenda);

            dadosConfirmacaoCanal = DadosConfirmacaoCanal.builder()
                    .numeroPedido(autorizacaoVenda.getOrdemPedido().getNumeroPedido())
                    .numeroOrdemExterno(autorizacaoVenda.getOrdemPedido().getNumeroOrdemExterno())
                    .chaveNFE(dadosConfirmacaoSefaz.getNfeKey())
                    .numeroNota(dadosConfirmacaoSefaz.getInvoiceNumber())
                    .dataEmissao(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS")))
                    .pdf(dadosConfirmacaoSefaz.getInvoice())
                    .status(MensagensNegociosEnum.PROCESSADO.getMensagem()).build();
            motivo = enviarDadosVendaRestOutPort.enviarDadosParaCanal(dadosConfirmacaoCanal);

        } catch (ExternalAPICallerFeignException e) {
            dadosConfirmacaoCanal = DadosConfirmacaoCanal.builder()
                    .status(MensagensNegociosEnum.ERRO.getMensagem()).build();
            motivo = "Venda " + autorizacaoVenda.getOrdemPedido().getNumeroOrdemExterno() + " recebida com erro";
        }

        salvarDadosVendaPersistenceOutPort.salvarDadosVenda(autorizacaoVenda, dadosConfirmacaoCanal, motivo);
    }

    public record Input(AutorizacaoVendaDTO dto) {
    }
}
