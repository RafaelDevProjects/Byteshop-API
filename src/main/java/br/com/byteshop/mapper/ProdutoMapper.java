package br.com.byteshop.mapper;

import br.com.byteshop.DTO.ProdutoRequestDTO;
import br.com.byteshop.DTO.ProdutoResponseDTO;
import br.com.byteshop.model.Produto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ProdutoMapper {
    ProdutoMapper INSTANCE = Mappers.getMapper(ProdutoMapper.class);

    Produto toEntity(ProdutoRequestDTO dto);

    @Mapping(target = "id", source = "id")
    ProdutoResponseDTO toResponseDTO(Produto produto);
}