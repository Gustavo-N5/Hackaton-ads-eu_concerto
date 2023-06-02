package br.com.eu_concerto.dtos.userDtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;

public record UserDtoAlter(
        String name,

        @Email
        String email,

        String cidade,

        String estado,

        @Pattern(regexp = "\\d{2}\\ ?\\d{8}")
        String telefone
) {
}
