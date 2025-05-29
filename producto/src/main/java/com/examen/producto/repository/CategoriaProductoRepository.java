package com.examen.producto.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.examen.producto.model.CategoriaProducto;

public interface CategoriaProductoRepository extends JpaRepository<CategoriaProducto, Integer> {
} 