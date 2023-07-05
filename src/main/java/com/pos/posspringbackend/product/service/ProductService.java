package com.pos.posspringbackend.product.service;

import com.pos.posspringbackend.product.entity.Product;

import java.util.List;

public interface ProductService {
    List<Product> getAllProducts();
    Product createProduct(Product product);
    Product findById(Integer id);
    Product update(Integer id, Product product);
    boolean delete(Integer id);
}
