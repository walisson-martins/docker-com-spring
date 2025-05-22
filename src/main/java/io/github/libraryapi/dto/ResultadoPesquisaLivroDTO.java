package io.github.libraryapi.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import io.github.libraryapi.enums.GeneroLivro;

public record ResultadoPesquisaLivroDTO(
        UUID id, String isbn, String titulo,
        LocalDate dataPublicacao, GeneroLivro genero,
        BigDecimal preco, AutorDTO autor) {

}
