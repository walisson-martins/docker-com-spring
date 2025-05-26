package io.github.libraryapi.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import io.github.libraryapi.dto.AutorDTO;
import io.github.libraryapi.model.Autor;

@Mapper(componentModel = "spring")
public interface AutorMapper {

    @Mapping(target = "nome", source = "nome")
    @Mapping(target = "dataNascimento", source = "dataNascimento")
    @Mapping(target = "nacionalidade", source = "nacionalidade")
    Autor toEntity(AutorDTO dto);

    AutorDTO toDTO(Autor autor);

}
