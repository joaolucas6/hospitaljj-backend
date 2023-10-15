package com.joaolucas.hospitalJJ.models.records;

public record RegisterRequest(
        String nome,
        String sobrenome,
        String email,
        String senha,
        String cpf
) {
}
