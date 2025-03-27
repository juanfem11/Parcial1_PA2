package com.parcial1.parcial1;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import com.parcial1.parcial1.model.Producto;
import com.parcial1.parcial1.repository.ProductoRepository;
import com.parcial1.parcial1.service.ProductoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

public class ProductoServiceTest {

    @Mock
    private ProductoRepository productoRepository;

    @InjectMocks
    private ProductoService productoService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void listarProductos_DeberiaRetornarListaDeProductos() {
        Producto producto1 = new Producto("1", "Laptop", 1500.00, 5);
        Producto producto2 = new Producto("2", "Mouse", 50.00, 10);

        when(productoRepository.findAll()).thenReturn(Flux.just(producto1, producto2));

        StepVerifier.create(productoService.listarProductos())
                .expectNext(producto1)
                .expectNext(producto2)
                .verifyComplete();

        verify(productoRepository, times(1)).findAll();
    }

    @Test
    void obtenerProductoPorId_DeberiaRetornarProductoSiExiste() {
        Producto producto = new Producto("1", "Laptop", 1500.00, 5);

        when(productoRepository.findById("1")).thenReturn(Mono.just(producto));

        StepVerifier.create(productoService.obtenerProductoPorId("1"))
                .expectNext(producto)
                .verifyComplete();

        verify(productoRepository, times(1)).findById("1");
    }

    @Test
    void crearProducto_DeberiaGuardarYRetornarProducto() {
        Producto producto = new Producto("1", "Laptop", 1500.00, 5);

        when(productoRepository.save(producto)).thenReturn(Mono.just(producto));

        StepVerifier.create(productoService.crearProducto(producto))
                .expectNext(producto)
                .verifyComplete();

        verify(productoRepository, times(1)).save(producto);
    }

    @Test
    void eliminarProducto_DeberiaEliminarProductoSiExiste() {
        String productoId = "1";

        when(productoRepository.deleteById(productoId)).thenReturn(Mono.empty());

        StepVerifier.create(productoService.eliminarProducto(productoId))
                .verifyComplete();

        verify(productoRepository, times(1)).deleteById(productoId);
    }
}