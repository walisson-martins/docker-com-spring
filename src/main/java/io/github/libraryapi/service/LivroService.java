package io.github.libraryapi.service;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import io.github.libraryapi.enums.GeneroLivro;
import io.github.libraryapi.model.Livro;
import io.github.libraryapi.model.Usuario;
import io.github.libraryapi.repository.LivroRepository;
import static io.github.libraryapi.repository.specs.LivroSpecs.anoPublicacaoEqual;
import static io.github.libraryapi.repository.specs.LivroSpecs.generoEqual;
import static io.github.libraryapi.repository.specs.LivroSpecs.isbnEqual;
import static io.github.libraryapi.repository.specs.LivroSpecs.nomeAutorLike;
import static io.github.libraryapi.repository.specs.LivroSpecs.tituloLike;
import io.github.libraryapi.security.SecurityService;
import io.github.libraryapi.validator.LivroValidator;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LivroService {

    @Autowired
    private final LivroRepository repository;

    @Autowired
    private final LivroValidator livroValidator;

    @Autowired
    private final SecurityService securityService;

    public Livro salvar(Livro livro) {
        livroValidator.validar(livro);
        Usuario usuario = securityService.obterUsuarioLogado();
        livro.setUsuario(usuario);
        return repository.save(livro);
    }

    public Optional<Livro> obterPorId(UUID id) {
        return repository.findById(id);

    }

    public void deletar(Livro livro) {
        repository.delete(livro);
    }

    public Page<Livro> pesquisa(
            String isbn, String titulo, String nomeAutor,
            GeneroLivro genero, Integer anoPublicacao, Integer pagina, Integer tamanhoPagina) {
        Specification<Livro> specs = Specification.where((root, query, cb) -> cb.conjunction());

        if (isbn != null) {
            specs = specs.and(isbnEqual(isbn));
        }

        if (titulo != null) {
            specs = specs.and(tituloLike(titulo));
        }

        if (genero != null) {
            specs = specs.and(generoEqual(genero));
        }

        if (anoPublicacao != null) {
            specs = specs.and(anoPublicacaoEqual(anoPublicacao));
        }

        if (nomeAutor != null) {
            specs = specs.and(nomeAutorLike(nomeAutor));
        }

        PageRequest pageRequest = PageRequest.of(pagina, tamanhoPagina);

        return repository.findAll(specs, pageRequest);

    }

    public void atualizar(Livro livro) {
        if (livro.getId() == null) {
            throw new IllegalArgumentException("Para atualizar, é necessário que o livro esteja salvo");
        }

        livroValidator.validar(livro);

        repository.save(livro);
    }
}
