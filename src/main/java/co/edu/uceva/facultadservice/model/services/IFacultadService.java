package co.edu.uceva.facultadservice.model.services;

import co.edu.uceva.facultadservice.model.entities.Facultad;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

/**
 * Interface que define los métodos que se pueden realizar sobre la entidad Producto
 */
public interface IFacultadService {
    Facultad save(Facultad facultad);
    void delete(Long id);
    Facultad findById(Long id);
    Facultad update(Facultad facultad);
    List<Facultad> findAll();
    Page<Facultad> findAll(Pageable pageable);
}
