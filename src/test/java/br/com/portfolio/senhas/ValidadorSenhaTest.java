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

    @Test
    void reconheceNumero() {
        assertTrue(validador.possuiNumero("Senha9!"));
    }

    @Test
    void reconheceDigitoUnicode() {
        assertTrue(validador.possuiNumero("Senha٣!"));
    }

    @Test
    void rejeitaSenhaSemNumero() {
        assertFalse(validador.possuiNumero("SenhaAbc!"));
    }

    @Test
    void naoConfundeSimboloComNumero() {
        assertFalse(validador.possuiNumero("!!!"));
    }

    @Test
    void rejeitaNuloNaRegraNumero() {
        assertFalse(validador.possuiNumero(null));
    }

    @Test
    void rejeitaVazioNaRegraNumero() {
        assertFalse(validador.possuiNumero(""));
    }

    @Test
    void reconheceExclamacaoComoEspecial() {
        assertTrue(validador.possuiCaractereEspecial("Abc!123"));
    }

    @Test
    void reconheceSublinhadoComoEspecial() {
        assertTrue(validador.possuiCaractereEspecial("Abc_123"));
    }

    @Test
    void rejeitaSenhaSemEspecial() {
        assertFalse(validador.possuiCaractereEspecial("Abc123"));
    }

    @Test
    void naoConsideraEspacoComoEspecial() {
        assertFalse(validador.possuiCaractereEspecial("Abc 123"));
    }

    @Test
    void rejeitaNuloNaRegraEspecial() {
        assertFalse(validador.possuiCaractereEspecial(null));
    }

    @Test
    void rejeitaVazioNaRegraEspecial() {
        assertFalse(validador.possuiCaractereEspecial(""));
    }

    @Test
    void aceitaSenhaSemEspacos() {
        assertTrue(validador.naoPossuiEspacos("Abc123!"));
    }

    @Test
    void rejeitaEspacoComum() {
        assertFalse(validador.naoPossuiEspacos("Abc 123!"));
    }

    @Test
    void rejeitaTabulacao() {
        assertFalse(validador.naoPossuiEspacos("Abc\t123!"));
    }

    @Test
    void rejeitaEspacoNaoSeparavel() {
        assertFalse(validador.naoPossuiEspacos("Abc\u00A0123!"));
    }

    @Test
    void rejeitaNuloNaRegraEspacos() {
        assertFalse(validador.naoPossuiEspacos(null));
    }

    @Test
    void rejeitaVazioNaRegraEspacos() {
        assertFalse(validador.naoPossuiEspacos(""));
    }
}
