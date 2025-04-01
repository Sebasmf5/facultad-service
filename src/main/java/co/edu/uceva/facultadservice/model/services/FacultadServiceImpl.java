package co.edu.uceva.facultadservice.model.services;

import co.edu.uceva.facultadservice.model.entities.Facultad;
import co.edu.uceva.facultadservice.model.repositories.IFacultadRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FacultadServiceImpl implements IFacultadService {

    IFacultadRepository facultadRepository;

    public FacultadServiceImpl(IFacultadRepository facultadRepository) {this.facultadRepository = facultadRepository;}


    @Override
    public Facultad save(Facultad facultad) {
        return facultadRepository.save(facultad);
    }

    @Override
    public void delete(Long id) {
        facultadRepository.deleteById(id);
    }

    @Override
    public Facultad findById(Long id) {
        return facultadRepository.findById(id).orElse(null);
    }

    @Override
    public Facultad update(Facultad facultad) {
        return facultadRepository.save(facultad);
    }

    @Override
    public List<Facultad> findAll() {
        return (List<Facultad>) facultadRepository.findAll();
    }
}
