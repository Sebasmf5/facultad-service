package co.edu.uceva.facultadservice.delivery.rest;

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

    private static final String ERROR = "error";
    private static final String MENSAJE = "mensaje";
    private static final String FACULTAD = "facultad";
    private static final String FACULTADES = "facultades";

    public FacultadRestController(IFacultadService facultadService) {this.facultadService = facultadService;}

    /**
     LISTAR TODOS LAS FACULTADES
     */
    @GetMapping("/facultades")
    public ResponseEntity<Map<String, Object>> getFacultades() {
        Map<String, Object> response = new HashMap<>();

        try{
            List<Facultad> facultades = facultadService.findAll();

            if (facultades.isEmpty()){
                response.put(MENSAJE, "La facultad no existe");
                response.put(FACULTADES, facultades); // para que sea siempre el mismo campo
                return ResponseEntity.status(HttpStatus.OK).body(response); //200 pero lista vacia
            }
            response.put(FACULTADES, facultades);
            return ResponseEntity.ok(response);
        }catch (DataAccessException d){
            response.put(MENSAJE, "Error al consultar la base de datos");
            response.put(ERROR, d.getMessage().concat(": ").concat(d.getMostSpecificCause().getMessage()));
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    /**
     * LISTAR CON PAGINACIÓN
     * */
    @GetMapping("/facultades/page/{page}")
    public ResponseEntity<Object> index(@PathVariable Integer page) {
        Map<String, Object> response = new HashMap<>();
        Pageable pageable = PageRequest.of(page, 4);

        try{
            Page<Facultad> facultades = facultadService.findAll(pageable);

            if (facultades.isEmpty()){
                response.put(MENSAJE, "No hay facultades en la pagina solicitada.");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }
            return ResponseEntity.ok(facultades);
        } catch (DataAccessException d) {
            response.put(MENSAJE, "Error al consultar la base de datos");
            response.put(ERROR, d.getMessage().concat(": ").concat(d.getMostSpecificCause().getMessage()));
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }catch (IllegalArgumentException i){
            response.put(MENSAJE, "Numero de pagina inválido.");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    /**
     * CREAR UNA NUEVA FACULTAD PASANDO EL OBJETO EN EL CUERPO DE LA PETICIÓN
     **/
    @PostMapping("/facultad")
    public ResponseEntity<Map<String, Object>> save(@RequestBody Facultad facultad, BindingResult result) {
        Map<String, Object> response = new HashMap<>();

        if(result.hasErrors()){
            List<String> errors = result.getFieldErrors()
                    .stream()
                    .map(err ->"El campo '" + err.getField() + "' " + err.getDefaultMessage())
                    .toList();
            response.put("errors", errors);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        try{
            //Guardar la facultad en la base de datos
            Facultad nuevaFacultad = facultadService.save(facultad);

            response.put(MENSAJE, "La facultad se ha guardado correctamente");
            response.put(FACULTAD, nuevaFacultad);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        }catch (DataAccessException d){
            response.put(MENSAJE, "Error al guardar la facultad en la base de datos.");
            response.put(ERROR, d.getMessage().concat(": ").concat(d.getMostSpecificCause().getMessage()));
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
    /**
     * EMIMINAR UNA FACULTAD PASANDO EL OBJETO EN EL CUERPO DE LA PETICIÓN
     **/
    @DeleteMapping("/facultad")
    public ResponseEntity<Map<String, Object>> delete(@RequestBody Long id) {
        Map<String, Object> response = new HashMap<>();

        try {
            Facultad facultadExistente = facultadService.findById(id);
            if (facultadExistente == null){
                response.put(MENSAJE, "La facultad con ID " + id + " no existe en la base de datos.");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }

            facultadService.delete(id);
            response.put(MENSAJE, "La facultad se ha eliminado correctamente");
            return ResponseEntity.ok(response);
        }catch (DataAccessException d){
            response.put(MENSAJE, "Error al eliminar la facultad en la base de datos.");
            response.put(ERROR, d.getMessage().concat(": ").concat(d.getMostSpecificCause().getMessage()));
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    /**
     * Actualizar un producto pasando el objeto en el cuerpo de la petición.
     * @param facultad: Objeto Facultad que se va a actualizar
     */
    @PutMapping("/facultad")
    public ResponseEntity<Map<String, Object>> update(@RequestBody Facultad facultad, BindingResult result) {
        Map<String, Object> response = new HashMap<>();

        if(result.hasErrors()){
            List<String> errors = result.getFieldErrors()
                    .stream()
                    .map(err ->"El campo '" + err.getField() + "' " + err.getDefaultMessage())
                    .toList();
            response.put("errors", errors);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        try{
            //Verificar si existe
            if (facultadService.findById(facultad.getId()) == null){
                response.put(MENSAJE, "ERROR: No se pudo editar, la facultad con ID: "+ facultad.getId()+" no existe en la base de datos.");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }
            //Guardar directamente la facultad actualizada en la base de datos
            Facultad facultadActualizada = facultadService.update(facultad);

            response.put(MENSAJE, "La facultad se ha actualizado correctamente");
            response.put(FACULTAD, facultadActualizada);
            return ResponseEntity.ok(response);
        }catch (DataAccessException d){
            response.put(MENSAJE,"Error al actualizar la facultad en la base de datos.");
            response.put(ERROR, d.getMessage().concat(": ").concat(d.getMostSpecificCause().getMessage()));
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
    /**
     * OBTENER LA FACULTAD POR SU ID
     **/
    @GetMapping("/facultad/{id}")
    public ResponseEntity<Map<String, Object>> findById(@PathVariable("id") Long id) {
        Map<String, Object> response = new HashMap<>();

        try{
            Facultad facultad = facultadService.findById(id);

            if (facultad == null){
                response.put(MENSAJE,"La facultad con ID: " + id + " no existe en la base de datos.");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }
            response.put(MENSAJE, "La facultad ha sido cargada correctamente");
            response.put(FACULTAD, facultad);
            return ResponseEntity.ok(response);
        }catch (DataAccessException d){
            response.put(MENSAJE, "Error al consultar la facultad en la base de datos.");
            response.put(ERROR, d.getMessage().concat(": ").concat(d.getMostSpecificCause().getMessage()));
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
}
