package com.examen.producto.model;

public enum EstadoProducto {
    ACTIVO("Activo"),
    INACTIVO("Inactivo"),
    AGOTADO("Agotado");

    private final String descripcion;

    EstadoProducto(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public static EstadoProducto fromString(String text) {
        for (EstadoProducto estado : EstadoProducto.values()) {
            if (estado.descripcion.equalsIgnoreCase(text)) {
                return estado;
            }
        }
        throw new IllegalArgumentException("Estado no válido: " + text);
    }
} 