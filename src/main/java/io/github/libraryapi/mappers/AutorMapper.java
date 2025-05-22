package io.github.libraryapi.mappers;

import org.mapstruct.Mapper;

import io.github.libraryapi.dto.AutorDTO;
import io.github.libraryapi.model.Autor;

@Mapper(componentModel = "spring")
public interface AutorMapper {

    Autor toEntity(AutorDTO dto);

    AutorDTO toDTO(Autor autor);

}
