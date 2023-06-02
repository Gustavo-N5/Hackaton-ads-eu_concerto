package br.com.eu_concerto.controller;

import br.com.eu_concerto.dtos.userDtos.UserDtoAlter;
import br.com.eu_concerto.dtos.userDtos.UserDtoCreate;
import br.com.eu_concerto.dtos.userDtos.UserResponse;
import br.com.eu_concerto.dtos.veiculosDtos.VeiculoDtoResponse;
import br.com.eu_concerto.dtos.veiculosDtos.VeiculosDtoCreate;
import br.com.eu_concerto.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.UUID;

@RestController
@RequestMapping("api/user")
public class UserController {

    private UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    // Metodo responsavel por cadastrar um usuario
    @PostMapping("/cadastro")
    public ResponseEntity<Object> createUser(@RequestBody @Valid UserDtoCreate userDto, UriComponentsBuilder uriBuilder) {
        var user = service.crateUser(userDto);

        var uri = uriBuilder.path("/api/user/{id}").buildAndExpand(user.getId()).toUri();

        return ResponseEntity.created(uri).body(user);
    }

    //Metodo GET vai buscar um usuario por ID
    @GetMapping("/{id}")
    public ResponseEntity<Object> findById(@PathVariable(value = "id") UUID id) {
        var user = service.findById(id);

        if (user.isPresent() && !user.get().isActive()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User is not active");
        }


        return user.<ResponseEntity<Object>>map(user1 -> ResponseEntity.status(HttpStatus.OK).body(user1)).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found."));
    }

    // Metodo responsavel pelo software delete
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable(value = "id") UUID id) {
        service.delete(id);
        return ResponseEntity.noContent().build();

    }

    // Metodo para alterar user
    @PatchMapping("/alterar/{id}")
    public ResponseEntity<Object> alterUser(@PathVariable(value = "id") UUID id ,@RequestBody @Valid UserDtoAlter userAlter){
      var user = service.alterUser(id, userAlter);

      return ResponseEntity.ok(new UserResponse(user));
    }

    //Está criando o veiculo porem ainda não está relacionando com o cliente
    //Precisa ser melhorado
    @PostMapping("/cadastro/veiculos/{id}")
    public ResponseEntity<Object> cadastroDeVeiculos(@RequestBody @Valid VeiculosDtoCreate veiculoCreate, @PathVariable(name = "id") UUID id, UriComponentsBuilder uriBuilder){
        var veiculo = service.createVeiculo(veiculoCreate, id);

        var uri = uriBuilder.path("/buscar-veiculo/{id_user}/{id_veiculo}").buildAndExpand(id,veiculo.getId()).toUri();


        return ResponseEntity.created(uri).body(veiculo);
    }

    //Metodo para buscar Carro
    // Por questões de tempo ainda não está funcionando pois o objeto não está ficando salvo no banco de usuario, implementação futura
    @GetMapping("/buscar-veiculo/{id_user}/{id_veiculo}")
    public ResponseEntity<Object> buscarVeiculo(@PathVariable(value = "id_user") UUID idUser, @PathVariable(value = "id_veicilo") UUID idVeiculo){
        var veiculo =service.findByVeiculo(idUser, idVeiculo);

        return ResponseEntity.ok(new VeiculoDtoResponse(veiculo));
    }

}
