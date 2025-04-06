package co.edu.uceva.facultadservice.domain.exception;

public class FacultadExistenteException extends RuntimeException {
    public FacultadExistenteException(String nombre) {
        super("La facultad " + nombre + " ya existe en la base de datos");
    }
}
