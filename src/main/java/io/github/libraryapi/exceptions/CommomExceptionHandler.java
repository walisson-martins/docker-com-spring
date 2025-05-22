package io.github.libraryapi.exceptions;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import io.github.libraryapi.dto.ErroCampo;
import io.github.libraryapi.dto.ErroResposta;
import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@Slf4j
public class CommomExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public ErroResposta handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        log.error("Erro de validação", e.getMessage());
        List<FieldError> fieldError = e.getFieldErrors();

        List<ErroCampo> listaErroCampos = fieldError.stream()
                .map(fe -> new ErroCampo(fe.getField(), fe.getDefaultMessage()))
                .collect(Collectors.toList());
        return new ErroResposta(HttpStatus.UNPROCESSABLE_ENTITY.value(), "Erro de validação", listaErroCampos);
    }

}
