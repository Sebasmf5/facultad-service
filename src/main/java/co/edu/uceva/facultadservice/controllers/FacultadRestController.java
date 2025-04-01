package co.edu.uceva.facultadservice.controllers;

import co.edu.uceva.facultadservice.model.entities.Facultad;
import co.edu.uceva.facultadservice.model.services.IFacultadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/facultad-service")
public class FacultadRestController {

    private IFacultadService facultadService;

    @Autowired
    public FacultadRestController(IFacultadService facultadService) {this.facultadService = facultadService;}

    @GetMapping("/facultades")
    public List<Facultad> getFacultades() {return facultadService.findAll();}

    @PostMapping("/facultad")
    public Facultad save(@RequestBody Facultad facultad) {return facultadService.save(facultad);}

    @DeleteMapping("/facultad")
    public void delete(@RequestBody Long id) {facultadService.delete(id);}

    @PutMapping("/facultad")
    public Facultad update(@RequestBody Facultad facultad) {return facultadService.update(facultad);}

    @GetMapping("/facultad/{id}")
    public Facultad findById(@PathVariable("id") Long id) {return facultadService.findById(id);}
}
