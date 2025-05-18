package io.github.libraryapi.repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import io.github.libraryapi.enums.GeneroLivro;
import io.github.libraryapi.model.Autor;
import io.github.libraryapi.model.Livro;

@SpringBootTest
class AutorRepositoryTest {

    @Autowired
    AutorRepository repository;

    @Autowired
    LivroRepository livroRepository;

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

    @Test
    void salvarAutorComLivroTest() {
        Autor autor = new Autor();
        autor.setNome("Antonio Carlos");
        autor.setNacionalidade("Americana");
        autor.setData_nascimento(LocalDate.of(1998, 05, 30));

        Livro livro1 = new Livro();
        livro1.setIsbn("124-454");
        livro1.setPreco(BigDecimal.valueOf(204));
        livro1.setGenero(GeneroLivro.MISTERIO);
        livro1.setTitulo("Casa assombrada");
        livro1.setData_publicacao(LocalDate.of(1999, 1, 12));
        livro1.setAutor(autor);

        Livro livro2 = new Livro();
        livro2.setIsbn("1212-454");
        livro2.setPreco(BigDecimal.valueOf(204));
        livro2.setGenero(GeneroLivro.FICCAO);
        livro2.setTitulo("LUA nova");
        livro2.setData_publicacao(LocalDate.of(1989, 1, 30));
        livro2.setAutor(autor);

        autor.setLivros(new ArrayList<>());
        autor.getLivros().add(livro1);
        autor.getLivros().add(livro2);

        repository.save(autor);
        livroRepository.saveAll(autor.getLivros());

    }

    @Test
    // @Transactional
    void listarLivrosAutor() {
        var id = UUID.fromString("68a61f5c-98e8-46ce-8934-a49af1a5e61a");
        var autor = repository.findById(id).get();

        List<Livro> livroLista = livroRepository.findByAutor(autor);
        autor.setLivros(livroLista);
        autor.getLivros().forEach(System.out::println);
    }
}
