package br.com.eu_concerto.models;

import br.com.eu_concerto.dtos.userDtos.UserDtoAlter;
import br.com.eu_concerto.dtos.userDtos.UserDtoCreate;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "user_general")
@Data
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
//Entidade que representa nosso usuario
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String name;

    private String email;

    private String password;

    private String cpf;

    private String cidade;

    private String estado;

    private String telefone;

    private boolean prestaServico;

    private boolean active;

    @OneToMany
    @Embedded
    @JoinColumn(name = "Veiculo_id")
    private List<Veiculo> veiculos;

    // Construtor que recebe uma DTO e instancia como User
    public User(UserDtoCreate userDto) {
        this.name = userDto.name();
        this.email = userDto.email();
        this.password = userDto.password();
        this.cpf = userDto.cpf();
        this.cidade = userDto.cidade();
        this.estado = userDto.estado();
        this.telefone = userDto.telefone();
        this.prestaServico = userDto.prestaServico();
        this.active = true;
    }

    // Função responsavel por fazer um software delete
    public void delete() {
        this.active = false;
    }

    public void alterUser(UserDtoAlter userAlter) {

        // Verificando se os atributos do DTO estão null, caso não estejão eles alteram
        if (userAlter.name() != null) {
            this.name = userAlter.name();
        }
        if (userAlter.email() != null) {
            this.email = userAlter.email();
        }
        if (userAlter.cidade() != null) {
            this.cidade = userAlter.cidade();
        }
        if (userAlter.estado() != null) {
            this.estado = userAlter.estado();
        }
        if (userAlter.telefone() != null) {
            this.telefone = userAlter.telefone();
        }
    }

    public void addVeiculo(Veiculo veiculo) {
        veiculos.add(veiculo);
    }

    public Veiculo findveiculo(UUID idVeiculo) {
        for (Veiculo i : veiculos) {
            if(i.getId().equals(idVeiculo)){
                return i;
            }
        }
        return null;
    }
}
