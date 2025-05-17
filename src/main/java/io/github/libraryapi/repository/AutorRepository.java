package io.github.libraryapi.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.github.libraryapi.model.Autor;

@Repository
public interface AutorRepository extends JpaRepository<Autor, UUID> {

}
