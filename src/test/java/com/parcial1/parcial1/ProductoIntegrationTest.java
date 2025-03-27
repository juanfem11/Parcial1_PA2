package com.parcial1.parcial1;

import com.parcial1.parcial1.model.Producto;
import com.parcial1.parcial1.repository.ProductoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

@SpringBootTest
@AutoConfigureWebTestClient
public class ProductoIntegrationTest {

    @Autowired
    private WebTestClient webTestClient;

    @Autowired
    private ProductoRepository productoRepository;

    @BeforeEach
    void setUp() {
        productoRepository.deleteAll().block();
    }

    @Test
    void crearProducto_DeberiaRetornarProductoCreado() {
        Producto producto = new Producto(null, "Teclado", 80.00, 7);

        webTestClient.post().uri("/api/productos")
                .body(Mono.just(producto), Producto.class)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.nombre").isEqualTo("Teclado")
                .jsonPath("$.precio").isEqualTo(80.00)
                .jsonPath("$.stock").isEqualTo(7);
    }

    @Test
    void obtenerProductoPorId_DeberiaRetornarProducto() {
        Producto producto = productoRepository.save(new Producto(null, "Monitor", 250.00, 3)).block();

        webTestClient.get().uri("/api/productos/" + producto.getId())
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.nombre").isEqualTo("Monitor")
                .jsonPath("$.precio").isEqualTo(250.00)
                .jsonPath("$.stock").isEqualTo(3);
    }

    @Test
    void eliminarProducto_DeberiaEliminarYRetornarVacio() {
        Producto producto = productoRepository.save(new Producto(null, "Mouse", 50.00, 10)).block();

        webTestClient.delete().uri("/api/productos/" + producto.getId())
                .exchange()
                .expectStatus().isOk();

        webTestClient.get().uri("/api/productos/" + producto.getId())
                .exchange()
                .expectStatus().isNotFound();
    }
}