package io.github.libraryapi;

import java.time.LocalDate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.github.libraryapi.model.Autor;
import io.github.libraryapi.repository.AutorRepository;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        var context = SpringApplication.run(Application.class, args);

        AutorRepository repository = context.getBean(AutorRepository.class);
        salvarExemplo(repository);
    }

    public static void salvarExemplo(AutorRepository autorRepository) {
        Autor autor = new Autor();
        autor.setNome("Adriana Jose");

        autor.setNacionalidade("Brasileira");
        autor.setData_nascimento(LocalDate.of(1950, 1, 31));
        var autorSalvo = autorRepository.save(autor);
        System.out.println("autorSalvo " + autorSalvo);
    }

}
