package com.examen.producto.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.examen.producto.exception.CategoriaNotFoundException;
import com.examen.producto.exception.ProductoNotFoundException;
import com.examen.producto.exception.StockInsuficienteException;
import com.examen.producto.model.Producto;
import com.examen.producto.model.EstadoProducto;
import com.examen.producto.repository.ProductoRepository;
import com.examen.producto.exception.EstadoInvalidoException;

@Service
public class ProductoService {

    private final ProductoRepository repository;
    private final CategoriaProductoService categoriaService;

    public ProductoService(ProductoRepository repository, CategoriaProductoService categoriaService) {
        this.repository = repository;
        this.categoriaService = categoriaService;
    }

    @Transactional(readOnly = true)
    public List<Producto> findAll() {
        return repository.findAll();
    }

    @Transactional(readOnly = true)
    public Producto findById(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new ProductoNotFoundException("No se encontró el producto con ID: " + id));
    }

    @Transactional
    public Producto create(Producto producto) {
        // Validar que la categoría existe
        categoriaService.findById(producto.getIdCategoria());
        producto.setEstadoProducto(EstadoProducto.ACTIVO.getDescripcion());
        return repository.save(producto);
    }

    @Transactional
    public Producto updateEstado(Integer id, String nuevoEstado) {
        Producto producto = findById(id);
        try {
            EstadoProducto estado = EstadoProducto.fromString(nuevoEstado);
            producto.setEstadoProducto(estado.getDescripcion());
            return repository.save(producto);
        } catch (IllegalArgumentException e) {
            throw new EstadoInvalidoException("Estado no válido: " + nuevoEstado);
        }
    }

    @Transactional
    public Producto aumentarStock(Integer id, Integer cantidad, BigDecimal precioCompra) {
        Producto producto = findById(id);
        
        // Actualizar stock
        producto.setStockActual(producto.getStockActual() + cantidad);
        
        // Actualizar precio de compra
        producto.setCostoCompra(precioCompra);
        
        // Calcular nuevo precio de venta (precio de compra + 25%)
        BigDecimal nuevoPrecioVenta = precioCompra.multiply(new BigDecimal("1.25"))
                .setScale(2, RoundingMode.HALF_UP);
        producto.setPrecioVenta(nuevoPrecioVenta);
        
        // Actualizar estado a Activo
        producto.setEstadoProducto(EstadoProducto.ACTIVO.getDescripcion());
        
        return repository.save(producto);
    }

    @Transactional
    public Producto disminuirStock(Integer id, Integer cantidad) {
        Producto producto = findById(id);
        
        if (producto.getStockActual() < cantidad) {
            throw new StockInsuficienteException(
                "Stock insuficiente. Stock actual: " + producto.getStockActual() + ", cantidad solicitada: " + cantidad);
        }
        
        producto.setStockActual(producto.getStockActual() - cantidad);
        
        // Actualizar estado si el stock llega a 0
        if (producto.getStockActual() == 0) {
            producto.setEstadoProducto(EstadoProducto.AGOTADO.getDescripcion());
        }
        
        return repository.save(producto);
    }
} 