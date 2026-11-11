package br.com.portfolio.senhas;

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
}
