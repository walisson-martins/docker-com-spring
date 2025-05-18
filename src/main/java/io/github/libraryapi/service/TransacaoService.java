package io.github.libraryapi.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.github.libraryapi.enums.GeneroLivro;
import io.github.libraryapi.model.Autor;
import io.github.libraryapi.model.Livro;
import io.github.libraryapi.repository.AutorRepository;
import io.github.libraryapi.repository.LivroRepository;

@Service
public class TransacaoService {

    @Autowired
    private AutorRepository autorRepository;

    @Autowired
    private LivroRepository livroRepository;

    @Transactional
    public void executar() {
        Autor autor = new Autor();
        autor.setNome("Vanessa");
        autor.setNacionalidade("Indiana");
        autor.setData_nascimento(LocalDate.of(1994, 1, 18));

        autorRepository.saveAndFlush(autor);

        Livro livro = new Livro();
        livro.setIsbn("12-454");
        livro.setPreco(BigDecimal.valueOf(400));
        livro.setGenero(GeneroLivro.MISTERIO);
        livro.setTitulo("Vanessa Livro");
        livro.setData_publicacao(LocalDate.of(2020, 2, 2));

        livro.setAutor(autor);

        livroRepository.saveAndFlush(livro);

        if (autor.getNome().equals("x")) {
            throw new RuntimeException("Rollback");
        }

    }

    @Transactional
    public void updateSemQuery() {
        var livro = livroRepository.findById(UUID.fromString("ed4a3c95-7689-42fa-a0e1-d2cb941c2a2c")).orElse(null);

        livro.setData_publicacao(LocalDate.of(2004, 6, 1));

    }
}
