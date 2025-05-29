package com.examen.producto.exception;

public class StockInsuficienteException extends RuntimeException {
    
    private final Integer errorCode;

    public StockInsuficienteException(String message) {
        super(message);
        this.errorCode = 3;
    }

    @Override
    public String getMessage() {
        return "Error code: " + this.errorCode + ", message: " + super.getMessage();
    }

    public Integer getErrorCode() {
        return errorCode;
    }
} 