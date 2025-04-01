package co.edu.uceva.facultadservice.model.repositories;

import co.edu.uceva.facultadservice.model.entities.Facultad;
import org.springframework.data.repository.CrudRepository;

import javax.swing.*;

public interface IFacultadRepository extends CrudRepository<Facultad, Long> {
}
