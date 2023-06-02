package br.com.eu_concerto.dtos.userDtos;

import br.com.eu_concerto.models.User;

import java.util.UUID;

public record UserResponse(
        UUID id,

        String name,

        String email,

        String cpf,

        String cidade,
        String estado,

        String telefone,

        boolean prestaServico,

        boolean active) {
    public UserResponse(User user) {
        this(user.getId(), user.getName(), user.getEmail(), user.getCpf(), user.getCidade(), user.getEstado(), user.getTelefone(), user.isPrestaServico(), user.isActive());
    }


}
