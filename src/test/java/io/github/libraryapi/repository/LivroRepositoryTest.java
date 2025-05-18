package io.github.libraryapi.repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import io.github.libraryapi.enums.GeneroLivro;
import io.github.libraryapi.model.Autor;
import io.github.libraryapi.model.Livro;
import jakarta.transaction.Transactional;

@SpringBootTest
class LivroRepositoryTest {

    @Autowired
    LivroRepository livroRepository;

    @Autowired
    AutorRepository autorRepository;

    @Test
    void salvarTest() {
        Livro livro = new Livro();
        livro.setIsbn("9084798-454");
        livro.setPreco(BigDecimal.valueOf(100));
        livro.setGenero(GeneroLivro.FICCAO);
        livro.setTitulo("UFO");
        livro.setData_publicacao(LocalDate.of(1989, 1, 2));

        // Autor autor = autorRepository.findById(UUID.fromString("5a887349-c8df-4c33-854a-9f961295aae1"))
        //         .orElse(null);
        // livro.setAutor(autor);
        Autor autor = new Autor();
        autor.setNome("Maria Jose");
        autor.setNacionalidade("Brasileira");
        autor.setData_nascimento(LocalDate.of(1950, 1, 31));
        livro.setAutor(autor);
        livroRepository.save(livro);
    }

    @Test
    public void atualizarAutorLivroTest() {

        UUID id = UUID.fromString("6571b227-e788-4c99-bcd3-38819657e83a");
        var livroParaAtualizar = livroRepository.findById(id)
                .orElse(null);

        UUID idAutor = UUID.fromString("5a887349-c8df-4c33-854a-9f961295aae1");
        Autor autor = autorRepository.findById(idAutor).orElse(null);
        livroParaAtualizar.setAutor(autor);

        livroRepository.save(livroParaAtualizar);

    }

    @Test
    void deletarTest() {
        UUID id = UUID.fromString("8134a35a-eea3-474a-b61d-1bed7d007c67");
        livroRepository.deleteById(id);
    }

    @Test
    @Transactional //trazer apenas aquilo que preciso transacional Select Apenas no que eu instancio
    void BuscarLivroTest() {
        UUID id = UUID.fromString("6571b227-e788-4c99-bcd3-38819657e83a");
        Livro livro = livroRepository.findById(id).orElse(null);
        System.out.println("Livro");
        System.out.println(livro.getTitulo());

        System.out.println("Autor");
        System.out.println(livro.getAutor().getNome());
    }
}
