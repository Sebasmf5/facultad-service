package co.edu.uceva.facultadservice.domain.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Facultad {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "No puede estar vacío")
    @Column(nullable = false)
    private Long idDecano;
    @NotEmpty(message = "No puede estar vacio")
    @Column(nullable = false)
    private String nombre;
}
