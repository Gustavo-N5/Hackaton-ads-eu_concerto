package br.com.eu_concerto.dtos.veiculosDtos;

import br.com.eu_concerto.models.TypeVeiculo;
import br.com.eu_concerto.models.Veiculo;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record VeiculoDtoResponse(

        Long id,

        TypeVeiculo tipoVeiculo,

        String placa,

        String marca,

        String modelo,

        String ano
) {
    public VeiculoDtoResponse(Veiculo veiculo){
        this(veiculo.getId(), veiculo.getTipoVeiculo(), veiculo.getPlaca(), veiculo.getMarca(), veiculo.getModelo(), veiculo.getAno());
    }
}
