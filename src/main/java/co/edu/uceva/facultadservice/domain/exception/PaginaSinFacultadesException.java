package co.edu.uceva.facultadservice.domain.exception;

public class PaginaSinFacultadesException extends RuntimeException {
    public PaginaSinFacultadesException(int page) {
        super("No hay facultades en la pagina solicitada: " + page);
    }
}
