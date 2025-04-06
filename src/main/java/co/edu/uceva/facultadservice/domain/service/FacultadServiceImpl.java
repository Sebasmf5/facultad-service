package co.edu.uceva.facultadservice.domain.service;

import co.edu.uceva.facultadservice.domain.model.Facultad;
import co.edu.uceva.facultadservice.domain.repository.IFacultadRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class FacultadServiceImpl implements IFacultadService {

    IFacultadRepository facultadRepository;

    public FacultadServiceImpl(IFacultadRepository facultadRepository) {this.facultadRepository = facultadRepository;}


    @Override
    @Transactional
    public Facultad save(Facultad facultad) {
        return facultadRepository.save(facultad);
    }

    @Override
    public void delete(Long id) {
        facultadRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Facultad> findById(Long id) {
        return facultadRepository.findById(id);
    }

    @Override
    @Transactional
    public Facultad update(Facultad facultad) {
        return facultadRepository.save(facultad);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Facultad> findAll() {
        return (List<Facultad>) facultadRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Facultad> findAll(Pageable pageable) {
        return facultadRepository.findAll(pageable);
    }
}
