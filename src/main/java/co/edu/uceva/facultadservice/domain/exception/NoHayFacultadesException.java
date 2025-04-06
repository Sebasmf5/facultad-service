package co.edu.uceva.facultadservice.domain.exception;

public class NoHayFacultadesException extends RuntimeException {
    public NoHayFacultadesException() {
        super("No se encontraron facultades en la base de datos");
    }
}
