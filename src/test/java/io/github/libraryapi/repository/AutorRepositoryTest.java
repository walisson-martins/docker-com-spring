package io.github.libraryapi.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import io.github.libraryapi.model.Autor;

@SpringBootTest
public class AutorRepositoryTest {

    @Autowired
    AutorRepository repository;

    @Test
    public void salvarTest() {
        Autor autor = new Autor();
        autor.setNome("Maria Jose");

        autor.setNacionalidade("Brasileira");
        autor.setData_nascimento(LocalDate.of(1950, 1, 31));
        var autorSalvo = repository.save(autor);
        System.out.println("autorSalvo " + autorSalvo);
    }

    @Test
    public void atualizarTest() {

        var id = UUID.fromString("4cf7c2ab-3bba-4d62-9ce1-89eae4c0f8f8");

        Optional<Autor> autor = repository.findById(id);

        if (autor.isPresent()) {
            Autor possivelAutor = autor.get();
            System.out.println("Dados do autor: ");
            System.out.println(possivelAutor);
            possivelAutor.setData_nascimento(LocalDate.of(1994, 1, 10));

            repository.save(possivelAutor);
        }

    }

    @Test
    public void listarTodos() {
        List<Autor> list = repository.findAll();
        list.forEach(System.out::println);
    }

    @Test
    public void countTest() {
        System.out.println("Contagem de autores " + repository.count());
    }

    @Test
    public void deletePorIdTest() {
        var id = UUID.fromString("2e55fd92-2b58-434b-9d16-dc3af4c50e1e");
        repository.deleteById(id);
    }

    @Test
    public void deleteTest() {
        var id = UUID.fromString("4cf7c2ab-3bba-4d62-9ce1-89eae4c0f8f8");
        var adriana = repository.findById(id).get();
        repository.delete(adriana);
    }
}
