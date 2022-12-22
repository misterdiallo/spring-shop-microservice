package com.misterdiallo.backend.productservice.repository;

import com.misterdiallo.backend.productservice.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<Product, String> {
}
