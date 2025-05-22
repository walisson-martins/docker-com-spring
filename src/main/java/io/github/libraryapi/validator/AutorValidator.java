package io.github.libraryapi.validator;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.github.libraryapi.exceptions.RegistroDuplicadoException;
import io.github.libraryapi.model.Autor;
import io.github.libraryapi.repository.AutorRepository;

@Component
public class AutorValidator {

    @Autowired
    private AutorRepository repository;

    public void validar(Autor autor) {
        if (existeAutorCadastrado(autor)) {
            throw new RegistroDuplicadoException("Autor já cadastrado!");
        }
    }

    private boolean existeAutorCadastrado(Autor autor) {
        Optional<Autor> autorEncontrado = repository.findByNomeAndDataNascimentoAndNacionalidade(autor.getNome(),
                autor.getDataNascimento(), autor.getNacionalidade());

        if (autor.getId() == null) {
            return autorEncontrado.isPresent();
        }

        return autor.getId().equals(autorEncontrado.get().getId()) && autorEncontrado.isPresent();

    }
}
