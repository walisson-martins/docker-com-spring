package io.github.libraryapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.github.libraryapi.model.Livro;
import io.github.libraryapi.repository.LivroRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LivroService {

    @Autowired
    private final LivroRepository repository;

    public Livro salvar(Livro livro) {
        return repository.save(livro);
    }
}
