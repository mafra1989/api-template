package br.com.renner.application.mapper;

import br.com.renner.domain.models.sefaz.DadosVenda;
import br.com.renner.domain.models.venda.AutorizacaoVenda;
import br.com.renner.infrastructure.adapters.input.rest.dtos.AutorizacaoVendaDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AutorizacaoVendaInputMapper {

	AutorizacaoVenda toDomain(AutorizacaoVendaDTO dto);

	@Mapping(target = "orderNumber", source = "ordemPedido.numeroPedido")
	@Mapping(target = "externalOrderNumber", source = "ordemPedido.numeroOrdemExterno")
	@Mapping(target = "customer.id", source = "cliente.id")
	@Mapping(target = "customer.name", source = "cliente.nome")
	@Mapping(target = "customer.document", source = "cliente.documento")
	@Mapping(target = "customer.documentType", source = "cliente.tipoDocumento")
	@Mapping(target = "customer.personType", source = "cliente.tipoPessoa")
	@Mapping(target = "customer.address", source = "cliente.endereco")
	@Mapping(target = "customer.addressNumber", source = "cliente.numeroEndereco")
	@Mapping(target = "customer.addressComplement", source = "cliente.complementoEndereco")
	@Mapping(target = "customer.district", source = "cliente.bairro")
	@Mapping(target = "customer.city", source = "cliente.cidade")
	@Mapping(target = "customer.state", source = "cliente.estado")
	@Mapping(target = "customer.country", source = "cliente.pais")
	@Mapping(target = "customer.zipCode", source = "cliente.cep")
	@Mapping(target = "customer.ibgeCode", source = "cliente.codigoIbge")
	@Mapping(target = "customer.phoneNumber", source = "cliente.telefone")
	@Mapping(target = "customer.email", source = "cliente.email")
	DadosVenda toDadosVendaDomain(AutorizacaoVendaDTO dto);

}
