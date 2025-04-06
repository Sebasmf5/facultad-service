package co.edu.uceva.facultadservice.domain.repository;

import co.edu.uceva.facultadservice.domain.model.Facultad;
import org.springframework.data.jpa.repository.JpaRepository;

/**
Interfaz que hereda de JpaRepository para realizar las
operaciones CRUD paginación y ordenamiento sobre la entidad Facultad
**/
public interface IFacultadRepository extends JpaRepository<Facultad, Long> {
}
