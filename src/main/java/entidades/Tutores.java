package entidades;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Tutores {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 150, nullable = false)
    private String nome;

    @Column(nullable = false, unique = true)
    private String cpf;

    @ManyToOne
    @JoinColumn
    private Cidade cidade;

    @Column(nullable = false)
    private String nomeRua;

    @Column(nullable = false)
    private Long numeroCasa;


}
