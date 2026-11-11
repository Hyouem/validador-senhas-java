package br.com.portfolio.senhas;

import java.util.ArrayList;
import java.util.List;

public class ValidadorSenha {

    public static final int COMPRIMENTO_MINIMO = 12;

    public boolean possuiComprimentoMinimo(String senha) {
        return senha != null
            && senha.length() >= COMPRIMENTO_MINIMO;
    }

    public boolean possuiLetraMaiuscula(String senha) {
        if (senha == null) {
            return false;
        }

        for (int i = 0; i < senha.length(); i++) {
            if (Character.isUpperCase(senha.charAt(i))) {
                return true;
            }
        }

        return false;
    }

    public boolean possuiLetraMinuscula(String senha) {
        if (senha == null) {
            return false;
        }

        for (int i = 0; i < senha.length(); i++) {
            if (Character.isLowerCase(senha.charAt(i))) {
                return true;
            }
        }

        return false;
    }

    public boolean possuiNumero(String senha) {
        if (senha == null) {
            return false;
        }

        for (int i = 0; i < senha.length(); i++) {
            if (Character.isDigit(senha.charAt(i))) {
                return true;
            }
        }

        return false;
    }

    public boolean possuiCaractereEspecial(String senha) {
        if (senha == null) {
            return false;
        }

        for (int i = 0; i < senha.length(); i++) {
            char caractere = senha.charAt(i);

            if (!Character.isLetterOrDigit(caractere)
                    && !Character.isWhitespace(caractere)
                    && !Character.isSpaceChar(caractere)) {
                return true;
            }
        }

        return false;
    }

    public boolean naoPossuiEspacos(String senha) {
        if (senha == null || senha.isEmpty()) {
            return false;
        }

        for (int i = 0; i < senha.length(); i++) {
            char caractere = senha.charAt(i);

            if (Character.isWhitespace(caractere)
                    || Character.isSpaceChar(caractere)) {
                return false;
            }
        }

        return true;
    }

    public ResultadoValidacao validar(String senha) {
        List<RegraSenha> falhas = new ArrayList<>();

        if (!possuiComprimentoMinimo(senha)) {
            falhas.add(RegraSenha.COMPRIMENTO_MINIMO);
        }

        if (!possuiLetraMaiuscula(senha)) {
            falhas.add(RegraSenha.LETRA_MAIUSCULA);
        }

        if (!possuiLetraMinuscula(senha)) {
            falhas.add(RegraSenha.LETRA_MINUSCULA);
        }

        if (!possuiNumero(senha)) {
            falhas.add(RegraSenha.NUMERO);
        }

        if (!possuiCaractereEspecial(senha)) {
            falhas.add(RegraSenha.CARACTERE_ESPECIAL);
        }

        if (!naoPossuiEspacos(senha)) {
            falhas.add(RegraSenha.SEM_ESPACOS);
        }

        return new ResultadoValidacao(falhas);
    }
}
