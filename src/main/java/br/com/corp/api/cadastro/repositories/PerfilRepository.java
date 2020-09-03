package br.com.corp.api.cadastro.repositories;

import br.com.corp.api.cadastro.models.entities.Perfil;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PerfilRepository extends JpaRepository<Perfil, Long> {
}
