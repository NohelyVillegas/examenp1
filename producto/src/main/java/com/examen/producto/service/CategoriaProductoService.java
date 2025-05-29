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

    private final CategoriaProductoRepository categoriaProductoRepository;

    @Autowired
    public CategoriaProductoService(CategoriaProductoRepository categoriaProductoRepository) {
        this.categoriaProductoRepository = categoriaProductoRepository;
    }

    @Transactional
    public CategoriaProducto createCategoria(CategoriaProducto categoria) {
        return categoriaProductoRepository.save(categoria);
    }

    @Transactional(readOnly = true)
    public List<CategoriaProducto> getAllCategorias() {
        return categoriaProductoRepository.findAll();
    }

    @Transactional(readOnly = true)
    public CategoriaProducto getCategoriaById(Integer id) {
        return categoriaProductoRepository.findById(id)
                .orElseThrow(() -> new CategoriaNotFoundException("Categoría no encontrada con ID: " + id));
    }

    @Transactional
    public CategoriaProducto updateCategoria(Integer id, CategoriaProducto categoriaDetails) {
        CategoriaProducto categoria = getCategoriaById(id);
        categoria.setNombreCategoria(categoriaDetails.getNombreCategoria());
        categoria.setDescripcion(categoriaDetails.getDescripcion());
        return categoriaProductoRepository.save(categoria);
    }

    @Transactional
    public void deleteCategoria(Integer id) {
        if (!categoriaProductoRepository.existsById(id)) {
            throw new CategoriaNotFoundException("Categoría no encontrada con ID: " + id);
        }
        categoriaProductoRepository.deleteById(id);
    }
} 