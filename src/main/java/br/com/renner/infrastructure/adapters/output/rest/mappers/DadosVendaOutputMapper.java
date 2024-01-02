package br.com.renner.infrastructure.adapters.output.rest.mappers;

import br.com.renner.domain.models.DadosConfirmacaoCanal;
import br.com.renner.domain.models.sefaz.DadosConfirmacaoSefaz;
import br.com.renner.domain.models.sefaz.DadosVenda;
import br.com.renner.domain.models.venda.AutorizacaoVenda;
import br.com.renner.infrastructure.adapters.output.persistence.entity.DadosVendaEntity;
import br.com.renner.infrastructure.adapters.output.rest.proxy.sefaz.DadosVendaResponseEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface DadosVendaOutputMapper {

	DadosConfirmacaoSefaz toDomain(DadosVendaResponseEntity responseEntity);

	@Mapping(target="canal", source = "autorizacaoVenda.canal")
	@Mapping(target="cogigoEmpresa", source = "autorizacaoVenda.empresa")
	@Mapping(target="cogigoLoja", source = "autorizacaoVenda.loja")
	@Mapping(target="numeroPdv", source = "autorizacaoVenda.pdv")
	@Mapping(target="numeroPedido", source = "autorizacaoVenda.ordemPedido.numeroPedido")
	@Mapping(target="numeroOrdemExterno", source = "autorizacaoVenda.ordemPedido.numeroOrdemExterno")
	@Mapping(target="valorTotal", source = "autorizacaoVenda.totalItens")
	@Mapping(target="quantidadeItem", source = "autorizacaoVenda.quantidadeItens")
	@Mapping(target="chaveNfe", source = "dadosConfirmacaoCanal.chaveNFE")
	@Mapping(target="numeroNota", source = "dadosConfirmacaoCanal.numeroNota")
	@Mapping(target="dataEmissao", source = "dadosConfirmacaoCanal.dataEmissao")
	@Mapping(target="pdf", source = "dadosConfirmacaoCanal.pdf")
	@Mapping(target="situacao", source = "dadosConfirmacaoCanal.status")
	DadosVendaEntity toEntity(AutorizacaoVenda autorizacaoVenda,
							  DadosConfirmacaoCanal dadosConfirmacaoCanal);

}
