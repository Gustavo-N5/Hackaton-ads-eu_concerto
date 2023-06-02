package br.com.eu_concerto.dtos.userDtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

//DTO: responsavel por receber os dados vindo pelo body
public record UserDtoCreate(

        @NotBlank
        String name,

        @Email
        @NotBlank
        String email,

        @NotBlank
        String password,

        @NotBlank
        @Pattern(regexp = "\\d{3}\\.?\\d{3}\\.?\\d{3}\\-?\\d{2}")
        String cpf,

        @NotBlank
        String cidade,

        @NotBlank
        String estado,

        @NotBlank
        @Pattern(regexp = "\\d{2}\\ ?\\d{8}")
        String telefone,

        @NotNull
        boolean prestaServico

) {
}
