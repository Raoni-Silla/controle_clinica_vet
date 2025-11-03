package entidades;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Cidade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 150, nullable = false)
    private String nome;

    @Column(length = 100)
    private String descricao;

    @ManyToOne
    @JoinColumn(name = "estado_id")
    private Uf uf;
}
