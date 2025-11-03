package entidades;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Veterinario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn
    private Cidade cidade;

    @Column(length = 100, nullable = false)
    private String nome;

    private String nomeDaRua;

    private int numeroCasa;



}
