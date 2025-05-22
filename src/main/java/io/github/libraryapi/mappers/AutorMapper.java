package io.github.libraryapi.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import io.github.libraryapi.dto.AutorDTO;
import io.github.libraryapi.model.Autor;

@Mapper(componentModel = "spring")
public interface AutorMapper {

    @Mapping(source = "nome", target = "nome")
    @Mapping(source = "dataNascimento", target = "dataNascimento")
    @Mapping(source = "nacionalidade", target = "nacionalidade")
    Autor toEntity(AutorDTO dto);

    AutorDTO toDTO(Autor autor);

}
