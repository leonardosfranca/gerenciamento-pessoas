package com.attus.dto;

import java.time.LocalDate;

public record PessoaDTO(
        String nomeCompleto,
        LocalDate dataNascimento
) {
}
