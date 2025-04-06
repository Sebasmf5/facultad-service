package co.edu.uceva.facultadservice.delivery.rest;

import co.edu.uceva.facultadservice.domain.exception.FacultadNoEncontradaException;
import co.edu.uceva.facultadservice.domain.exception.NoHayFacultadesException;
import co.edu.uceva.facultadservice.domain.exception.PaginaSinFacultadesException;
import co.edu.uceva.facultadservice.domain.exception.ValidationException;
import co.edu.uceva.facultadservice.domain.model.Facultad;
import co.edu.uceva.facultadservice.domain.service.IFacultadService;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/facultad-service")
public class FacultadRestController {
    //Declaramos como final el servicio para mejorar la inmutabilidad
    private final IFacultadService facultadService;

    //Constantes para los mensajes de error
    private static final String ERROR = "error";
    private static final String MENSAJE = "mensaje";
    private static final String FACULTAD = "facultad";
    private static final String FACULTADES = "facultades";

    public FacultadRestController(IFacultadService facultadService) {
        this.facultadService = facultadService;
    }

    /**
     LISTAR TODOS LAS FACULTADES
     */
    @GetMapping("/facultades")
    public ResponseEntity<Map<String, Object>> getFacultades() {
        List<Facultad> facultades = facultadService.findAll();

        if (facultades.isEmpty()) {
            throw new NoHayFacultadesException();
        }
        Map<String, Object> response = new HashMap<>();
        response.put(FACULTADES, facultades);
        return ResponseEntity.ok(response);
    }

    /**
     * LISTAR CON PAGINACIÓN
     * */
    @GetMapping("/facultades/page/{page}")
    public ResponseEntity<Object> index(@PathVariable Integer page) {
        Pageable pageable = PageRequest.of(page, 4);
        Page<Facultad> facultades = facultadService.findAll(pageable);

        if (facultades.isEmpty()) {
            throw new PaginaSinFacultadesException(page);
        }
        return ResponseEntity.ok(facultades);
    }

    /**
     * CREAR UNA NUEVA FACULTAD PASANDO EL OBJETO EN EL CUERPO DE LA PETICIÓN
     **/
    @PostMapping("/facultad")
    public ResponseEntity<Map<String, Object>> save(@RequestBody Facultad facultad, BindingResult result) {

        if (result.hasErrors()) {
            throw new ValidationException(result);
        }
        Map<String, Object> response = new HashMap<>();
        Facultad nuevaFacultad = facultadService.save(facultad);
        response.put(MENSAJE, "La facultad se ha registrado correctamente");
        response.put(FACULTAD, nuevaFacultad);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    /**
     * EMIMINAR UNA FACULTAD PASANDO EL OBJETO EN EL CUERPO DE LA PETICIÓN
     **/
    @DeleteMapping("/facultad")
    public ResponseEntity<Map<String, Object>> delete(@RequestBody Long id) {

        facultadService.findById(id).orElseThrow(() -> new FacultadNoEncontradaException(id));
        facultadService.delete(id);
        Map<String, Object> response = new HashMap<>();
        response.put(MENSAJE, "La facultad se ha eliminado correctamente");
        response.put(FACULTAD, null);
        return ResponseEntity.ok(response);
    }

    /**
     * Actualizar un producto pasando el objeto en el cuerpo de la petición.
     * @param facultad: Objeto Facultad que se va a actualizar
     */
    @PutMapping("/facultad")
    public ResponseEntity<Map<String, Object>> update(@RequestBody Facultad facultad, BindingResult result) {

        if (result.hasErrors()) {
            throw new ValidationException(result);
        }
        facultadService.findById(facultad.getId())
                .orElseThrow(() -> new FacultadNoEncontradaException(facultad.getId()));

        Map<String, Object> response = new HashMap<>();
        Facultad facultadActualizada = facultadService.update(facultad);

        response.put(MENSAJE, "La facultad se ha actualizado correctamente");
        response.put(FACULTAD, facultadActualizada);
        return ResponseEntity.ok(response);

    }
    /**
     * OBTENER LA FACULTAD POR SU ID
     **/
    @GetMapping("/facultad/{id}")
    public ResponseEntity<Map<String, Object>> findById(@PathVariable("id") Long id) {

        Facultad facultad = facultadService.findById(id).
                orElseThrow(() -> new FacultadNoEncontradaException(id));
        Map<String, Object> response = new HashMap<>();
        response.put(MENSAJE, "La facultad se ha obtenido correctamente");
        response.put(FACULTAD, facultad);
        return ResponseEntity.ok(response);

    }
}
