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

    @Test
    void reconheceLetraMaiuscula() {
        assertTrue(validador.possuiLetraMaiuscula("javaA123"));
    }

    @Test
    void reconheceMaiusculaAcentuada() {
        assertTrue(validador.possuiLetraMaiuscula("javaÉ123"));
    }

    @Test
    void rejeitaMaiusculaAusente() {
        assertFalse(validador.possuiLetraMaiuscula("java123"));
    }

    @Test
    void rejeitaNumerosComoMaiusculas() {
        assertFalse(validador.possuiLetraMaiuscula("123456!"));
    }

    @Test
    void rejeitaNuloNaRegraMaiuscula() {
        assertFalse(validador.possuiLetraMaiuscula(null));
    }

    @Test
    void rejeitaVazioNaRegraMaiuscula() {
        assertFalse(validador.possuiLetraMaiuscula(""));
    }

    @Test
    void reconheceLetraMinuscula() {
        assertTrue(validador.possuiLetraMinuscula("JAVa123"));
    }

    @Test
    void reconheceMinusculaAcentuada() {
        assertTrue(validador.possuiLetraMinuscula("JAVAá123"));
    }

    @Test
    void rejeitaMinusculaAusente() {
        assertFalse(validador.possuiLetraMinuscula("JAVA123"));
    }

    @Test
    void rejeitaNumerosComoMinusculas() {
        assertFalse(validador.possuiLetraMinuscula("123456!"));
    }

    @Test
    void rejeitaNuloNaRegraMinuscula() {
        assertFalse(validador.possuiLetraMinuscula(null));
    }

    @Test
    void rejeitaVazioNaRegraMinuscula() {
        assertFalse(validador.possuiLetraMinuscula(""));
    }
}
