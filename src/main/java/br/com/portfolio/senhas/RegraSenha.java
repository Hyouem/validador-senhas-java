package br.com.portfolio.senhas;

public enum RegraSenha {

    COMPRIMENTO_MINIMO("Pelo menos 12 caracteres"),
    LETRA_MAIUSCULA("Pelo menos uma letra maiuscula"),
    LETRA_MINUSCULA("Pelo menos uma letra minuscula"),
    NUMERO("Pelo menos um numero"),
    CARACTERE_ESPECIAL("Pelo menos um caractere especial"),
    SEM_ESPACOS("Nao conter espacos em branco");

    private final String descricao;

    RegraSenha(String descricao) {
        this.descricao = descricao;
    }

    public String descricao() {
        return descricao;
    }
}