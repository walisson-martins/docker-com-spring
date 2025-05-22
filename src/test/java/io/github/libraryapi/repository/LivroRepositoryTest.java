package io.github.libraryapi.repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
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
        livro.setDataPublicacao(LocalDate.of(1989, 1, 2));

        Autor autor = autorRepository.findById(UUID.fromString("05c900ea-d239-478f-b1a3-3c4f9a94c10a"))
                .orElse(null);
        // livro.setAutor(autor);
        // Autor autor = new Autor();
        // autor.setNome("Maria Jose");
        // autor.setNacionalidade("Brasileira");
        // autor.setDataNascimento(LocalDate.of(1950, 1, 31));
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
    @Transactional // trazer apenas aquilo que preciso transacional Select Apenas no que eu
                   // instancio
    void BuscarLivroTest() {
        UUID id = UUID.fromString("6571b227-e788-4c99-bcd3-38819657e83a");
        Livro livro = livroRepository.findById(id).orElse(null);
        System.out.println("Livro");
        System.out.println(livro.getTitulo());

        System.out.println("Autor");
        System.out.println(livro.getAutor().getNome());
    }

    @Test
    void pesquisarPorTituloTest() {
        List<Livro> lista = livroRepository.findByTitulo("UFO");
        lista.forEach(System.out::println);
    }

    @Test
    void pesquisarPorTituloEPrecoTest() {
        var preco = BigDecimal.valueOf(100);
        List<Livro> lista = livroRepository.findByTituloAndPreco("UFO", preco);
        lista.forEach(System.out::println);
    }

    @Test
    void listarLivrosComQuery() {
        var resultado = livroRepository.litarTodosOrdenadoPorTituloEPreco();
        resultado.forEach(System.out::println);
    }

    @Test
    void listarAutoresDosLivros() {
        var resultado = livroRepository.listarAutoresDosLivros();
        resultado.forEach(System.out::println);
    }

    @Test
    void listarTitulosDiferentes() {
        var resultado = livroRepository.listarAutoresDosLivros();
        resultado.forEach(System.out::println);
    }

    @Test
    void listarGenerosAutoresBrasileiros() {
        var resultado = livroRepository.listarGenerosAutoresBrasileiros();
        resultado.forEach(System.out::println);
    }

    @Test
    void listarPorGeneroQueryParam() {
        var resultado = livroRepository.findByGenero(GeneroLivro.FICCAO, "dataPublicacao");
        resultado.forEach(System.out::println);
    }

    @Test
    void listarPorGeneroPositionalQueryParam() {
        var resultado = livroRepository.findByGeneroPositionalParams(GeneroLivro.MISTERIO, "preco");
        resultado.forEach(System.out::println);
    }

    @Test
    void deletePorGeneroTest() {
        livroRepository.deleteByGenero(GeneroLivro.MISTERIO);
    }

    @Test
    void updateDataAtualizacao() {
        livroRepository.updateDataAtualizacao(LocalDate.of(2000, 5, 12));
    }

}
