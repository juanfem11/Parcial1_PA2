package com.parcial1.parcial1.controller;

import com.parcial1.parcial1.model.Producto;
import com.parcial1.parcial1.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @GetMapping
    public Flux<Producto> listarProductos() {
        return productoService.listarProductos();
    }

    @GetMapping("/{id}")
    public Mono<Producto> obtenerProducto(@PathVariable String id) {
        return productoService.obtenerProductoPorId(id);
    }

    @PostMapping
    public Mono<Producto> crearProducto(@RequestBody Producto producto) {
        return productoService.crearProducto(producto);
    }

    @DeleteMapping("/{id}")
    public Mono<Void> eliminarProducto(@PathVariable String id) {
        return productoService.eliminarProducto(id);
    }
}