package entidades;


import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Animal {

    public Animal(){}

    public Animal(String nome, Raca raca, Tutores tutor, int idade, String peso) {
        setNome(nome);
        setRaca(raca);
        setTutor(tutor);
        setIdade(idade);
        setPeso(peso);
    }

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

    public void setNome(String nome) {
        if (nome == null || nome.equals("")) {
            throw new RuntimeException("nome invalido");
        }else {
            this.nome = nome;
        }
    }

    public void setRaca(Raca raca) {
        if (raca == null) {
            throw new RuntimeException("raca invalido");
        }else  {
            this.raca = raca;
        }

    }

    public void setTutor(Tutores tutor) {
        if (tutor == null) {
            throw new RuntimeException("tutor invalido");
        }else   {
            this.tutor = tutor;
        }
    }

    public void setIdade(int idade) {
        if (idade < 0) {
            throw new RuntimeException("idade invalido");
        } else if (idade > 200) {
            throw new RuntimeException("idade invalido");
        }else  {
            this.idade = idade;
        }
    }

    public void setPeso(String peso) {
        if (peso == null || peso.equals("")) {
            throw new RuntimeException("peso invalido");
        }else  {
            this.peso = peso;
        }
    }
}
