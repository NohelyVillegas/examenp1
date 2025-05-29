package com.examen.producto.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.examen.producto.model.Producto;

public interface ProductoRepository extends JpaRepository<Producto, Integer> {
} 