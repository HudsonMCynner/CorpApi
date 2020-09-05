package br.com.corp.api.cadastro.models.entities;


import br.com.corp.api.cadastro.models.enums.TipoAcesso;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "pessoa", schema = "cadastro")
@AllArgsConstructor
@Data
@NoArgsConstructor
public class Pessoa implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_pessoa")
    @SequenceGenerator(name = "seq_pessoa", sequenceName = "pessoa_id_seq", schema = "cadastro", allocationSize = 1)
    private Long id;

    @Column(length = 70, nullable = false)
    @NotNull
    private String nome;

    @Column(length = 100, nullable = false)
    @NotNull
    private String email;

    @Column(nullable = false)
    @NotNull
    private LocalDate nascimento;

    @Column(length = 14, nullable = false)
    @NotNull
    private String telefone;

    @Column(length = 14, nullable = false)
    @NotNull
    private String cpf;

    @Column(length = 20, nullable = false)
    @NotNull
    private String senha;

    @Column
    @NotNull
    private TipoAcesso tipoAcesso = TipoAcesso.USUARIO;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_perfil", referencedColumnName = "id")
    private Perfil perfil;
}
