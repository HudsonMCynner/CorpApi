package br.com.corp.api.cadastro.repositories;

import br.com.corp.api.cadastro.models.entities.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

    @Query("SELECT pf FROM Pessoa pf WHERE pf.cpf = ?1")
    Pessoa buscarPessoaPorCpf(String cpf);
}
