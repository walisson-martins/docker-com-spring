package io.github.libraryapi.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import io.github.libraryapi.exceptions.OperacaoNaoPermitidaException;
import io.github.libraryapi.model.Autor;
import io.github.libraryapi.repository.AutorRepository;
import io.github.libraryapi.repository.LivroRepository;
import io.github.libraryapi.validator.AutorValidator;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AutorService {

    @Autowired
    private final AutorRepository repository;

    @Autowired
    private final AutorValidator validator;

    @Autowired
    private final LivroRepository livroRepository;

    public Autor salvar(Autor autor) {
        validator.validar(autor);
        return repository.save(autor);
    }

    public void atualizar(Autor autor) {
        if (autor.getId() == null) {
            throw new IllegalArgumentException("Para atualizar é necessário que o Autor esteja salvo na base");
        }
        validator.validar(autor);
        repository.save(autor);

    }

    public Optional<Autor> obterPorId(UUID id) {
        return repository.findById(id);
    }

    public void deletar(Autor autor) {
        if (possuiLivro(autor)) {
            throw new OperacaoNaoPermitidaException("Não é permitido excluir, Autor possui livros cadastrados");
        }
        repository.delete(autor);
    }

    public List<Autor> pesquisar(String nome, String nacionalidade) {
        if (nome != null && nacionalidade != null) {
            return repository.findByNomeAndNacionalidade(nome, nacionalidade);
        }

        if (nome != null) {
            return repository.findByNome(nome);
        }

        if (nacionalidade != null) {
            return repository.findByNacionalidade(nacionalidade);
        }

        return repository.findAll();

    }

    public List<Autor> pesquisaByExample(String nome, String nacionalidade) {
        var autor = new Autor();
        autor.setNome(nome);
        autor.setNacionalidade(nacionalidade);

        ExampleMatcher matcher = ExampleMatcher.matching().withIgnoreCase().withIgnoreNullValues()
                .withIgnorePaths("id", "dataNascimento", "dataCadastro")
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);

        Example<Autor> autorExample = Example.of(autor, matcher);

        return repository.findAll(autorExample);

    }

    public boolean possuiLivro(Autor autor) {

        return livroRepository.existsByAutor(autor);

    }
}
