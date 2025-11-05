package entidades;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
public class Consulta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne
    @JoinColumn(name = "fkAnimal")
    private Animal animal;

    @ManyToOne
    @JoinColumn(name = "fkVet")
    private Veterinario veterinario;

    @Column(nullable = false)
    private LocalDate dataConsulta;

    @Column(nullable = false)
    private LocalDateTime dataAtual;

    @Column(nullable = false)
    private Double valorConsulta;

    public Consulta() {}

    public Consulta (Animal animal, Veterinario veterinario, LocalDate dataConsulta, Double valorConsulta) {
        setAnimal(animal);
        setVeterinario(veterinario);
        setDataConsulta(dataConsulta);
        setValorConsulta(valorConsulta);
        this.dataAtual = LocalDateTime.now();
    }




    public void setDataConsulta(LocalDate dataConsulta) {
        if (dataConsulta.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("Consulta marcada em data invalida");
        }
        this.dataConsulta = dataConsulta;
    }

    public void setValorConsulta(Double valorConsulta) {
        if (valorConsulta <= 0) {
            throw new IllegalArgumentException("preço da consulta invalido");
        }
        this.valorConsulta = valorConsulta;
    }
}
