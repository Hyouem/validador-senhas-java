package br.com.portfolio.senhas;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.io.StringReader;
import java.nio.charset.StandardCharsets;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    private String executar(String comandos) throws IOException {
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();

        try (PrintStream saida = new PrintStream(
                buffer, true, StandardCharsets.UTF_8)) {

            BufferedReader entrada = new BufferedReader(
                new StringReader(comandos)
            );

            Main.executar(entrada, saida, null);
        }

        return buffer.toString(StandardCharsets.UTF_8);
    }

    private int contar(String texto, String trecho) {
        return (texto.length() - texto.replace(trecho, "").length())
            / trecho.length();
    }

    @Test
    void apresentaMenuEPermiteSair() throws IOException {
        String saida = executar("0\n");

        assertTrue(saida.contains("1 - Validar senha"));
        assertTrue(saida.contains("0 - Sair"));
        assertTrue(saida.contains("Ate a proxima!"));
        assertFalse(saida.contains("RESULTADO DA VALIDACAO"));
    }

    @Test
    void apresentaSeisRegrasAprovadas() throws IOException {
        String saida = executar("1\nSenhaSegura@2026\n0\n");

        assertEquals(6, contar(saida, "[OK]"));
        assertEquals(0, contar(saida, "[FALHA]"));
        assertTrue(saida.contains("Resultado: SENHA VALIDA"));
    }

    @Test
    void identificaSenhaApenasMuitoCurta() throws IOException {
        String saida = executar("1\nAa1!\n0\n");

        assertTrue(saida.contains("[FALHA] Pelo menos 12 caracteres"));
        assertEquals(1, contar(saida, "[FALHA]"));
        assertTrue(saida.contains("Resultado: SENHA INVALIDA"));
    }

    @Test
    void identificaQuatroRegrasReprovadas() throws IOException {
        String saida = executar("1\nabcdef\n0\n");

        assertEquals(4, contar(saida, "[FALHA]"));
        assertEquals(2, contar(saida, "[OK]"));
        assertTrue(saida.contains("Resultado: SENHA INVALIDA"));
    }

    @Test
    void permiteDuasValidacoesSeguidas() throws IOException {
        String saida = executar(
            "1\nAa1!\n1\nSenhaSegura@2026\n0\n"
        );

        assertEquals(2, contar(saida, "RESULTADO DA VALIDACAO"));
        assertTrue(saida.contains("Resultado: SENHA INVALIDA"));
        assertTrue(saida.contains("Resultado: SENHA VALIDA"));
    }

    @Test
    void rejeitaOpcaoInvalidaSemEncerrar() throws IOException {
        String saida = executar("9\n0\n");

        assertTrue(saida.contains("Opcao invalida."));
        assertTrue(saida.contains("Ate a proxima!"));
    }

    @Test
    void aceitaOpcaoComEspacosNasPontas() throws IOException {
        String saida = executar(" 1 \nSenhaSegura@2026\n 0 \n");

        assertTrue(saida.contains("Resultado: SENHA VALIDA"));
        assertTrue(saida.contains("Ate a proxima!"));
    }

    @Test
    void rejeitaSenhaVazia() throws IOException {
        String saida = executar("1\n\n0\n");

        assertEquals(6, contar(saida, "[FALHA]"));
        assertTrue(saida.contains("Resultado: SENHA INVALIDA"));
    }

    @Test
    void encerraQuandoEntradaDoMenuAcaba() throws IOException {
        String saida = executar("");

        assertTrue(saida.contains("Ate a proxima!"));
        assertFalse(saida.contains("RESULTADO DA VALIDACAO"));
    }

    @Test
    void encerraQuandoEntradaDaSenhaAcaba() throws IOException {
        String saida = executar("1\n");

        assertTrue(saida.contains("Entrada encerrada."));
        assertFalse(saida.contains("RESULTADO DA VALIDACAO"));
    }

    @Test
    void naoIncluiSenhaNoRelatorio() throws IOException {
        String senha = "NaoMostrarEstaSenha@123";
        String saida = executar("1\n" + senha + "\n0\n");

        assertFalse(saida.contains(senha));
        assertTrue(saida.contains("RESULTADO DA VALIDACAO"));
    }
}