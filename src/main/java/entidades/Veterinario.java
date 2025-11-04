package entidades;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
public class Veterinario {


    public Veterinario() {}
    public Veterinario(String nome, String cpf, String nomeDaRua, int numeroCasa, Cidade cidade, LocalDate dataNascimento) {

        setNome(nome);
        setCpf(cpf);
        setNomeDaRua(nomeDaRua);
        setNumeroCasa(numeroCasa);
        setCidade(cidade);
        setDataNascimento(dataNascimento);

    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn
    private Cidade cidade;

    @Column(length = 100, nullable = false)
    private String nome;

    private String cpf;

    private String nomeDaRua;

    private LocalDate dataNascimento;

    private int numeroCasa;



}
