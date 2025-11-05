package entidades;

import infra.Utilitarios;
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

    public void setCpf(String cpf) {
        if (cpf == null){
            throw new IllegalArgumentException("cpf nulo");
        }
        //remove tudo que nao for digito
        String cpfNumeros = cpf.replaceAll("\\D", "");

        if (cpfNumeros.length() != 11) {
            throw new IllegalArgumentException("CPF deve conter 11 dígitos");
        }

        // Verifica se todos os dígitos são iguais
        if (cpfNumeros.matches("(\\d)\\1{10}")) {
            throw new IllegalArgumentException("CPF inválido (todos dígitos iguais)");
        }

        if (Utilitarios.isCpfValido(cpfNumeros)) {
           this.cpf = cpf;
        }else  throw new IllegalArgumentException("CPF inválido");

    }


}
