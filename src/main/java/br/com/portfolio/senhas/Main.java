package br.com.portfolio.senhas;

import java.io.BufferedReader;
import java.io.Console;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader entrada = new BufferedReader(
            new InputStreamReader(System.in, StandardCharsets.UTF_8)
        );

        executar(entrada, System.out, System.console());
    }

    static void executar(
            BufferedReader entrada,
            PrintStream saida,
            Console console) throws IOException {

        ValidadorSenha validador = new ValidadorSenha();

        saida.println("=== VALIDADOR DE SENHAS ===");
        saida.println("Utilize apenas senhas ficticias.");

        while (true) {
            saida.println();
            saida.println("1 - Validar senha");
            saida.println("0 - Sair");
            saida.print("Opcao: ");

            String opcao = console == null
                ? entrada.readLine()
                : console.readLine();

            if (opcao == null || opcao.trim().equals("0")) {
                saida.println("Ate a proxima!");
                return;
            }

            if (!opcao.trim().equals("1")) {
                saida.println("Opcao invalida. Tente novamente.");
                continue;
            }

            String senha;

            if (console == null) {
                saida.print("Digite uma senha de teste: ");
                senha = entrada.readLine();
            } else {
                char[] caracteres = console.readPassword(
                    "Digite uma senha de teste: "
                );

                if (caracteres == null) {
                    saida.println("Entrada encerrada.");
                    return;
                }

                try {
                    senha = new String(caracteres);
                } finally {
                    Arrays.fill(caracteres, '\0');
                }
            }

            if (senha == null) {
                saida.println("Entrada encerrada.");
                return;
            }

            ResultadoValidacao resultado = validador.validar(senha);

            saida.println();
            saida.println("RESULTADO DA VALIDACAO");

            for (RegraSenha regra : RegraSenha.values()) {
                String status = resultado.atende(regra)
                    ? "[OK] "
                    : "[FALHA] ";

                saida.println(status + regra.descricao());
            }

            saida.println(
                resultado.valida()
                    ? "Resultado: SENHA VALIDA"
                    : "Resultado: SENHA INVALIDA"
            );
        }
    }
}