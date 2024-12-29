package dev.fordragon.forohub.api.infra.validation;

public class CustomValidationException extends RuntimeException {

    public CustomValidationException (String mensaje) {
        super(mensaje);
    }
}
