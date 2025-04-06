package co.edu.uceva.facultadservice.domain.exception;

public class FacultadNoEncontradaException extends RuntimeException {
    public FacultadNoEncontradaException(Long id) {
        super("La facultad con ID " + id + " no existe en la base de datos");
    }
}
