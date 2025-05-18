package io.github.libraryapi.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import io.github.libraryapi.service.TransacaoService;

@SpringBootTest
public class TransactionTest {

    @Autowired
    TransacaoService transacaoService;

    /*
     * @Transacional
     */
    @Test
    void transcaoSimples() {
        transacaoService.executar();
    }

    @Test
    void TransacaoEstadoManaged() {
        transacaoService.updateSemQuery();
    }
}
