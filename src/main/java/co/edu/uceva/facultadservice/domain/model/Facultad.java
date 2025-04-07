package co.edu.uceva.facultadservice.domain.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Facultad {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Debe ingresar el ID del decano")
    @Column(nullable = false)
    private Long idDecano;
    @NotEmpty(message = "Debe ingresar el nombre de la facultad")
    @Column(nullable = false)
    private String nombre;
}
