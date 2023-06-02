package br.com.eu_concerto.models;

import br.com.eu_concerto.dtos.veiculosDtos.VeiculosDtoCreate;
import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Embeddable
@Data
@Entity
@Table(name = "veiculo")
//Entidade que representa o
public class Veiculo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Enumerated
    private TypeVeiculo tipoVeiculo;
    private String placa;
    private String marca;
    private String modelo;

    private String ano;

    public Veiculo(VeiculosDtoCreate veiculoCreate) {
        this.tipoVeiculo = veiculoCreate.tipoVeiculo();
        this.placa = veiculoCreate.placa();
        this.marca = veiculoCreate.marca();
        this.modelo = veiculoCreate.modelo();
        this.ano = veiculoCreate.ano();
    }
}
