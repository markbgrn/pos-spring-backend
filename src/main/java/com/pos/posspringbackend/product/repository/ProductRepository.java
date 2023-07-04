package com.pos.posspringbackend.product.repository;

import com.pos.posspringbackend.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {

}
