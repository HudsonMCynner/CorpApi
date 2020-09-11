package br.com.corp.api.cadastro.models.entities;

import br.com.corp.api.cadastro.models.enums.TipoAcesso;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name = "perfil", schema = "cadastro")
@AllArgsConstructor
@Data
@NoArgsConstructor
public class Perfil implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_perfil")
    @SequenceGenerator(name = "seq_perfil", sequenceName = "perfil_id_seq", schema = "cadastro", allocationSize = 1)
    private Long id;

    @Column(length = 20, nullable = false)
    @NotNull
    private String nick;

    @Column(name = "steam_id", length = 20, nullable = false)
    @NotNull
    private String steamId;

    @Lob
    @Type(type = "org.hibernate.type.TextType")
    private String avatar;

    @Lob
    @Type(type = "org.hibernate.type.TextType")
    private String cfg1;

    @Lob
    @Type(type = "org.hibernate.type.TextType")
    private String cfg2;

    @Lob
    @Type(type = "org.hibernate.type.TextType")
    private String cfg3;
}
