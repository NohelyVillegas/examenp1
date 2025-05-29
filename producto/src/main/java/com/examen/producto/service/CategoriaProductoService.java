package com.examen.producto.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.examen.producto.exception.CategoriaNotFoundException;
import com.examen.producto.model.CategoriaProducto;
import com.examen.producto.repository.CategoriaProductoRepository;

@Service
public class CategoriaProductoService {

    private final CategoriaProductoRepository repository;

    @Autowired
    public CategoriaProductoService(CategoriaProductoRepository repository) {
        this.repository = repository;
    }

    @Transactional(readOnly = true)
    public List<CategoriaProducto> findAll() {
        return repository.findAll();
    }

    @Transactional(readOnly = true)
    public CategoriaProducto findById(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new CategoriaNotFoundException("No se encontró la categoría con ID: " + id));
    }

    @Transactional
    public CategoriaProducto create(CategoriaProducto categoria) {
        return repository.save(categoria);
    }

    @Transactional
    public CategoriaProducto update(Integer id, CategoriaProducto categoria) {
        CategoriaProducto existente = findById(id);
        existente.setNombreCategoria(categoria.getNombreCategoria());
        existente.setDescripcion(categoria.getDescripcion());
        return repository.save(existente);
    }

    @Transactional
    public void delete(Integer id) {
        if (!repository.existsById(id)) {
            throw new CategoriaNotFoundException("No se encontró la categoría con ID: " + id);
        }
        repository.deleteById(id);
    }
} 