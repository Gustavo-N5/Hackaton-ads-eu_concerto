package br.com.eu_concerto.service;

import br.com.eu_concerto.dtos.userDtos.UserDtoAlter;
import br.com.eu_concerto.dtos.userDtos.UserDtoCreate;
import br.com.eu_concerto.dtos.veiculosDtos.VeiculosDtoCreate;
import br.com.eu_concerto.models.User;
import br.com.eu_concerto.models.Veiculo;
import br.com.eu_concerto.repositories.UserRepository;
import br.com.eu_concerto.repositories.VeiculoRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
// Logica de implmentação
public class UserService {


    private UserRepository repository;
    private VeiculoRepository repositoryVeiculo;


    public UserService(UserRepository repository, VeiculoRepository repositoryVeiculo) {
        this.repository = repository;
        this.repositoryVeiculo = repositoryVeiculo;
    }

    // Busca referenciando um ojeto do tipo User
    public User getUserId(UUID id){
        return  repository.getReferenceById(id);
    }

    // Busca retornadno um objeto do tipo Optional<Object>
    public Optional<User> findById(UUID id){
        return repository.findById(id);
    }

    // Criando um usuario
    @Transactional
    public User crateUser(UserDtoCreate userDto){
        var user = new User(userDto);

        return repository.save(user);
    }

    // Deletando um usuario
    @Transactional
    public  void delete(UUID id){
        var user = getUserId(id);
        user.delete();
    }

    // Alterando um usuario atraves da instancia
    @Transactional
    public User alterUser(UUID id, UserDtoAlter userAlter) {
        var user = getUserId(id);
        user.alterUser(userAlter);
        return user;
    }

    // Criando um veiculo
    public Veiculo createVeiculo(VeiculosDtoCreate veiculoCreate, UUID id) {
        var veiculo = new Veiculo(veiculoCreate);

        repositoryVeiculo.save(veiculo);

        var user = getUserId(id);

        user.addVeiculo(veiculo);

        return veiculo;

    }

    //Buscando um veiculo
    public Veiculo findByVeiculo(UUID idUser, UUID idVeiculo) {
        var user = getUserId(idUser);


        return user.findveiculo(idVeiculo);
    }
}
