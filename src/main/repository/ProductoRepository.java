package com.parcial1.parcial1.repository;

import com.parcial1.parcial1.model.Producto;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoRepository extends ReactiveMongoRepository<Producto, String> {
}