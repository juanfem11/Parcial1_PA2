package com.parcial1.parcial1;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

public class HelloControllerTest {

    @InjectMocks
    private HelloController helloController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSayHello() {
        String response = helloController.sayHello();
        assertEquals("¡Hola, Spring Boot con Maven!", response);
    }

    @Test
    void testSayHelloNotEmpty() {
        String response = helloController.sayHello();
        assertEquals(false, response.isEmpty());
    }
}