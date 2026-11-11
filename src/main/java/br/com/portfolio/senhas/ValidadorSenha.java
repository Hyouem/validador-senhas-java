package br.com.portfolio.senhas;

public class ValidadorSenha {

    public static final int COMPRIMENTO_MINIMO = 12;

    public boolean possuiComprimentoMinimo(String senha) {
        return senha != null
            && senha.length() >= COMPRIMENTO_MINIMO;
    }
}