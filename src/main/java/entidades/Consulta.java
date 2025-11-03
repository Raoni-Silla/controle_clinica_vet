package entidades;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

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
    private Double valorConsulta;

}
