package co.edu.uceva.facultadservice.domain.model;

public record UsuarioDTO(Long id, String nombreCompleto, String correo, Rol rol) {
    public enum Rol {
        DOCENTE, ESTUDIANTE, COORDINADOR, DECANO, ADMINISTRADOR
    }
}
