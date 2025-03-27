package com.parcial1.parcial1.controller;

import com.parcial1.parcial1.model.Producto;
import com.parcial1.parcial1.repository.ProductoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    private final ProductoRepository productoRepository;

    public ProductoController(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    // Obtener todos los productos
    @GetMapping
    public Flux<Producto> obtenerProductos() {
        return productoRepository.findAll();
    }

    // Obtener un producto por ID
    @GetMapping("/{id}")
    public Mono<ResponseEntity<Producto>> obtenerProductoPorId(@PathVariable String id) {
        return productoRepository.findById(id)
                .map(producto -> ResponseEntity.ok(producto))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    // Crear un nuevo producto
    @PostMapping
    public Mono<ResponseEntity<Producto>> crearProducto(@RequestBody Producto producto) {
        return productoRepository.save(producto)
                .map(savedProducto -> new ResponseEntity<>(savedProducto, HttpStatus.CREATED));
    }

    // **Eliminar un producto por ID**
    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Void>> eliminarProducto(@PathVariable String id) {
        return productoRepository.findById(id)
                .flatMap(producto -> productoRepository.delete(producto)
                        .then(Mono.just(new ResponseEntity<Void>(HttpStatus.NO_CONTENT))) // 204 si se elimina
                )
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND)); // 404 si no existe
    }
}