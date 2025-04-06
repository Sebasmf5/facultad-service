package co.edu.uceva.facultadservice.domain.exception;

import org.springframework.validation.BindingResult;

import javax.naming.Binding;

public class ValidationException extends RuntimeException {
    public final BindingResult result;
    public ValidationException(BindingResult result) {
        super("Error de validación de datos.");
        this.result = result;
    }
}
