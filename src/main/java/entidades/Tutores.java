package entidades;

import infra.Utilitarios;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

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

    private LocalDate dataNascimento;


    public Tutores(){}
    
    public Tutores(String nome, String cpf, Long numeroCasa, String nomeRua, Cidade cidade, LocalDate dataNascimento) {
        setNome(nome);
        setCpf(cpf);
        setNumeroCasa(numeroCasa);
        setNomeRua(nomeRua);
        setCidade(cidade);
        setDataNascimento(dataNascimento);
    }

    public void setNome(String nome) {
        if (nome == null){
            throw new IllegalArgumentException("nome nulo");
        }
        this.nome = nome;
    }
    
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
            throw new IllegalArgumentException("CPF inválido");
        }

        this.cpf = cpf;
        }

        public void setCidade(Cidade cidade) {

        if (cidade == null){
            this.cidade = cidade;
        }
        throw new IllegalArgumentException("cidade nulo");
        }


    }

