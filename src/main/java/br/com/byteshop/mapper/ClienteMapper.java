package br.com.byteshop.mapper;

import br.com.byteshop.model.Cliente;
import br.com.byteshop.DTO.ClienteRequestDTO;
import br.com.byteshop.DTO.ClienteResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ClienteMapper {
    ClienteMapper INSTANCE = Mappers.getMapper(ClienteMapper.class);

    Cliente toEntity(ClienteRequestDTO dto);

    @Mapping(target = "id", source = "id")
    ClienteResponseDTO toResponseDTO(Cliente cliente);
}