package br.com.portfolio.senhas;

import java.util.List;
import java.util.Objects;

public record ResultadoValidacao(List<RegraSenha> regrasNaoAtendidas) {

    public ResultadoValidacao {
        regrasNaoAtendidas = List.copyOf(regrasNaoAtendidas);
    }

    public boolean valida() {
        return regrasNaoAtendidas.isEmpty();
    }

    public boolean atende(RegraSenha regra) {
        Objects.requireNonNull(regra, "regra");
        return !regrasNaoAtendidas.contains(regra);
    }
}