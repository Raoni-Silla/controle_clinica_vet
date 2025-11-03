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

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Animal animal;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Tutores tutor;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Veterinario veterinario;

    @Column(nullable = false)
    private LocalDate dataConsulta;

    @Column(nullable = false)
    private Double valorConsulta;

}
