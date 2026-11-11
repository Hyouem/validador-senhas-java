package br.com.portfolio.senhas;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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

    @Test
    void validaSenhaCompleta() {
        ResultadoValidacao resultado = validador.validar("SenhaSegura@2026");

        assertTrue(resultado.valida());
        assertTrue(resultado.regrasNaoAtendidas().isEmpty());

        for (RegraSenha regra : RegraSenha.values()) {
            assertTrue(resultado.atende(regra));
        }
    }

    @Test
    void identificaFalhaSomenteNoComprimento() {
        ResultadoValidacao resultado = validador.validar("Aa1!");

        assertFalse(resultado.valida());
        assertEquals(
            List.of(RegraSenha.COMPRIMENTO_MINIMO),
            resultado.regrasNaoAtendidas()
        );
        assertFalse(resultado.atende(RegraSenha.COMPRIMENTO_MINIMO));
        assertTrue(resultado.atende(RegraSenha.LETRA_MAIUSCULA));
    }

    @Test
    void identificaAusenciaDeMaiuscula() {
        assertEquals(
            List.of(RegraSenha.LETRA_MAIUSCULA),
            validador.validar("senhasegura@2026").regrasNaoAtendidas()
        );
    }

    @Test
    void identificaAusenciaDeMinuscula() {
        assertEquals(
            List.of(RegraSenha.LETRA_MINUSCULA),
            validador.validar("SENHASEGURA@2026").regrasNaoAtendidas()
        );
    }

    @Test
    void identificaAusenciaDeNumero() {
        assertEquals(
            List.of(RegraSenha.NUMERO),
            validador.validar("SenhaSegura@XYZ").regrasNaoAtendidas()
        );
    }

    @Test
    void identificaAusenciaDeEspecial() {
        assertEquals(
            List.of(RegraSenha.CARACTERE_ESPECIAL),
            validador.validar("SenhaSegura2026").regrasNaoAtendidas()
        );
    }

    @Test
    void identificaEspacoComum() {
        assertEquals(
            List.of(RegraSenha.SEM_ESPACOS),
            validador.validar("Senha Segura@2026").regrasNaoAtendidas()
        );
    }

    @Test
    void identificaTabulacao() {
        assertEquals(
            List.of(RegraSenha.SEM_ESPACOS),
            validador.validar("Senha\tSegura@2026").regrasNaoAtendidas()
        );
    }

    @Test
    void identificaEspacoNaoSeparavel() {
        assertEquals(
            List.of(RegraSenha.SEM_ESPACOS),
            validador.validar("Senha\u00A0Segura@2026").regrasNaoAtendidas()
        );
    }

    @Test
    void rejeitaNuloSemLancarExcecao() {
        ResultadoValidacao resultado = validador.validar(null);

        assertFalse(resultado.valida());
        assertEquals(
            Set.of(RegraSenha.values()),
            Set.copyOf(resultado.regrasNaoAtendidas())
        );
    }

    @Test
    void rejeitaSenhaVaziaComTodasAsFalhas() {
        ResultadoValidacao resultado = validador.validar("");

        assertFalse(resultado.valida());
        assertEquals(
            RegraSenha.values().length,
            resultado.regrasNaoAtendidas().size()
        );
    }

    @Test
    void identificaVariasFalhasSimultaneamente() {
        ResultadoValidacao resultado = validador.validar("abcdef");

        assertEquals(
            List.of(
                RegraSenha.COMPRIMENTO_MINIMO,
                RegraSenha.LETRA_MAIUSCULA,
                RegraSenha.NUMERO,
                RegraSenha.CARACTERE_ESPECIAL
            ),
            resultado.regrasNaoAtendidas()
        );
    }

    @Test
    void resultadoNaoPermiteModificarLista() {
        ResultadoValidacao resultado = validador.validar("Aa1!");

        assertThrows(
            UnsupportedOperationException.class,
            () -> resultado.regrasNaoAtendidas().add(RegraSenha.NUMERO)
        );
    }

    @Test
    void resultadoFazCopiaDefensiva() {
        List<RegraSenha> falhas = new ArrayList<>();
        falhas.add(RegraSenha.NUMERO);

        ResultadoValidacao resultado = new ResultadoValidacao(falhas);

        falhas.clear();

        assertEquals(
            List.of(RegraSenha.NUMERO),
            resultado.regrasNaoAtendidas()
        );
        assertFalse(resultado.valida());
    }
}
