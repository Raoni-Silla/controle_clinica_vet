package entidades;


import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Animal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn
    private Raca raca;

    @ManyToOne
    @JoinColumn
    private Tutores tutor;

    private String nome;

    private int idade;

    private String peso;

}
