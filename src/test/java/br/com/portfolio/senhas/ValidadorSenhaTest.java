package br.com.portfolio.senhas;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ValidadorSenhaTest {

    private final ValidadorSenha validador = new ValidadorSenha();

    @Test
    void aceitaExatamenteDozeCaracteres() {
        assertTrue(validador.possuiComprimentoMinimo("123456789012"));
    }

    @Test
    void aceitaMaisDeDozeCaracteres() {
        assertTrue(validador.possuiComprimentoMinimo("1234567890123"));
    }

    @Test
    void rejeitaOnzeCaracteres() {
        assertFalse(validador.possuiComprimentoMinimo("12345678901"));
    }

    @Test
    void rejeitaSenhaVazia() {
        assertFalse(validador.possuiComprimentoMinimo(""));
    }

    @Test
    void rejeitaSenhaNula() {
        assertFalse(validador.possuiComprimentoMinimo(null));
    }

    @Test
    void comprimentoNaoAvaliaPresencaDeEspacos() {
        assertTrue(validador.possuiComprimentoMinimo("            "));
    }
}