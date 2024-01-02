package br.com.renner.infrastructure.adapters.output.rest.mappers;

import br.com.renner.domain.models.MatrizTributaria;
import br.com.renner.infrastructure.adapters.output.rest.proxy.matriztributaria.MatrizTributariaResponseEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MatrizTributariaOutputMapper {

	MatrizTributaria toDomain(MatrizTributariaResponseEntity responseEntity);

}
