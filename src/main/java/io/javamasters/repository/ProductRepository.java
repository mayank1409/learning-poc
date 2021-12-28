package io.javamasters.repository;

import org.springframework.data.repository.CrudRepository;

import io.javamasters.entity.Product;

public interface ProductRepository extends CrudRepository<Product, Integer> {

}
