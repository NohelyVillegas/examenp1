package com.examen.producto.controller;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.examen.producto.model.Producto;
import com.examen.producto.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    private final ProductoService productoService;

    @Autowired
    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Producto> getProducto(@PathVariable Integer id) {
        return ResponseEntity.ok(productoService.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<Producto>> getAllProductos() {
        return ResponseEntity.ok(productoService.findAll());
    }

    @PostMapping
    public ResponseEntity<Producto> createProducto(@RequestBody Producto producto) {
        return ResponseEntity.ok(productoService.create(producto));
    }

    @PutMapping("/{id}/estado")
    public ResponseEntity<Producto> updateEstado(
            @PathVariable Integer id,
            @RequestParam String nuevoEstado) {
        return ResponseEntity.ok(productoService.updateEstado(id, nuevoEstado));
    }

    @PutMapping("/{id}/aumentar-stock")
    public ResponseEntity<Producto> aumentarStock(
            @PathVariable Integer id,
            @RequestParam Integer cantidad,
            @RequestParam BigDecimal precioCompra) {
        return ResponseEntity.ok(productoService.aumentarStock(id, cantidad, precioCompra));
    }

    @PutMapping("/{id}/disminuir-stock")
    public ResponseEntity<Producto> disminuirStock(
            @PathVariable Integer id,
            @RequestParam Integer cantidad) {
        return ResponseEntity.ok(productoService.disminuirStock(id, cantidad));
    }
} 