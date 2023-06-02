package br.com.eu_concerto.repositories;

import br.com.eu_concerto.models.Veiculo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;
// Conexão com o banco através da JPA
public interface VeiculoRepository extends JpaRepository<Veiculo, UUID> {
}
