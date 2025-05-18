package io.github.libraryapi.repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import io.github.libraryapi.enums.GeneroLivro;
import io.github.libraryapi.model.Autor;
import io.github.libraryapi.model.Livro;

@Repository
public interface LivroRepository extends JpaRepository<Livro, UUID> {

    //Query Method
    List<Livro> findByAutor(Autor autor);

    List<Livro> findByTitulo(String titulo);

    List<Livro> findByTituloAndPreco(String titulo, BigDecimal preco);

    // List<Livro> findByDataPublicacaoBetween(LocalDate inicio, LocalDate fim);
    @Query("select l from Livro as l order by l.titulo, l.preco")
    List<Livro> litarTodosOrdenadoPorTituloEPreco();

    @Query("select a from Livro as l join l.autor a")
    List<Autor> listarAutoresDosLivros();

    @Query("select distinct l.titulo from Livro as l")
    List<String> listarNomesDiferentesLivros();

    @Query("""
                    select l.genero
                        from Livro l
                        join l.autor a
                        where a.nacionalidade = 'Brasileira'
                        order by l.genero
            """)
    List<String> listarGenerosAutoresBrasileiros();

    @Query("select l from Livro l where l.genero = :genero order by :ordenacao")
    List<Livro> findByGenero(
            @Param("genero") GeneroLivro generoLivro,
            @Param("ordenacao") String ordenacao);

    @Query("select l from Livro l where l.genero = ?1 order by ?2")
    List<Livro> findByGeneroPositionalParams(GeneroLivro generoLivro, String ordenacao);
}
