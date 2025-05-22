package io.github.libraryapi.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import org.hibernate.validator.constraints.ISBN;

import io.github.libraryapi.enums.GeneroLivro;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;

public record CadastroLivroDTO(
        @NotBlank(message = "campo obrigatório") @ISBN String isbn,
        @NotBlank(message = "campo obrigatório") String titulo,
        @NotNull(message = "campo obrigatório") @Past(message = "Não pode ser data futura") LocalDate dataPublicacao,
        GeneroLivro genero,
        BigDecimal preco,
        @NotNull(message = "campo obrigatório") UUID idAutor) {

}
