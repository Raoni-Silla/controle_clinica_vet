package entidades;


import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Uf {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(length = 2, nullable = false, unique = true)
    private String sigla;

    @Column(length = 30,unique = true)
    private String nome;

}
