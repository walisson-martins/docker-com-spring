package io.github.libraryapi.mappers;

import org.mapstruct.Mapper;

import io.github.libraryapi.dto.UsuarioDTO;
import io.github.libraryapi.model.Usuario;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {

    Usuario toEntity(UsuarioDTO dto);
}
