package co.edu.uceva.facultadservice.model.services;

import co.edu.uceva.facultadservice.model.entities.Facultad;

import java.util.List;

public interface IFacultadService {
    Facultad save(Facultad facultad);
    void delete(Long id);
    Facultad findById(Long id);
    Facultad update(Facultad facultad);
    List<Facultad> findAll();
}
