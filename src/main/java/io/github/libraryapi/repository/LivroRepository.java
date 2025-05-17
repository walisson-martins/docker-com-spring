package io.github.libraryapi.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.github.libraryapi.model.Livro;

@Repository
public interface LivroRepository extends JpaRepository<Livro, UUID> {

}
