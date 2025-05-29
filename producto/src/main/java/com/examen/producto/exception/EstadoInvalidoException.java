package com.examen.producto.exception;

public class EstadoInvalidoException extends RuntimeException {
    
    private final Integer errorCode;

    public EstadoInvalidoException(String message) {
        super(message);
        this.errorCode = 4;
    }

    @Override
    public String getMessage() {
        return "Error code: " + this.errorCode + ", message: " + super.getMessage();
    }

    public Integer getErrorCode() {
        return errorCode;
    }
} 