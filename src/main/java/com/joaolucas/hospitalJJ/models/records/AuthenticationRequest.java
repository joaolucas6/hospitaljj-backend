package com.joaolucas.hospitalJJ.models.records;

public record AuthenticationRequest(
        String email,
        String senha
) {
}
