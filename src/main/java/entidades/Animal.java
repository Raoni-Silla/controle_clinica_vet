package entidades;


import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Animal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;



    @OneToOne(cascade = CascadeType.PERSIST)
    private Raca raca;

    private String nome;

    private int idade;

    private String peso;

}
