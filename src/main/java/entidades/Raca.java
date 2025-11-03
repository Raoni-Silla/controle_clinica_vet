package entidades;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Raca {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(length = 120, nullable = false)
    private String nome;

    @Column(length = 120)
    private String descricao;

}
