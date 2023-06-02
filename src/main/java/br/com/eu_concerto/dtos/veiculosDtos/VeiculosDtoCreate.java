package br.com.eu_concerto.dtos.veiculosDtos;

import br.com.eu_concerto.models.TypeVeiculo;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record VeiculosDtoCreate(

        @NotNull
        TypeVeiculo tipoVeiculo,

        @NotBlank
        String placa,

        @NotBlank
        String marca,

        @NotBlank
        String modelo,

        @JsonFormat(pattern = "yyyy")
        String ano
) {
}
