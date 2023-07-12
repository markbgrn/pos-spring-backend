package com.pos.posspringbackend.product.service.impl;

import com.pos.posspringbackend.category.entity.Category;
import com.pos.posspringbackend.category.repository.CategoryRepository;
import com.pos.posspringbackend.product.entity.Product;
import com.pos.posspringbackend.product.repository.ProductRepository;
import com.pos.posspringbackend.product.service.ProductService;
import com.pos.posspringbackend.utils.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product createProduct(Product product) {
        Category category = categoryRepository.findById(product.getCategory().getId()).get();
        product.setCategory(category);
        Product savedProduct = Product.builder()
                .prodId(product.getProdId())
                .prodName(product.getProdName())
                .prodDesc(product.getProdDesc())
                .unitPrice(product.getUnitPrice())
                .quantity(product.getQuantity())
                .category(product.getCategory())
                .build();
        return productRepository.save(savedProduct);
    }

    @Override
    public Product findById(Integer id) {
        Optional<Product> product = productRepository.findById(id);
        return product.orElseThrow(() -> new ResourceNotFoundException("Product with id: " + id + " not found"));
    }

    @Override
    public Product update(Integer id, Product product) {
        Product savedProduct = productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product with id " + id + " not found"));
        savedProduct.setProdName(product.getProdName());
        savedProduct.setProdDesc(product.getProdDesc());
        savedProduct.setCategory(product.getCategory());
        savedProduct.setUnitPrice(product.getUnitPrice());
        savedProduct.setQuantity(product.getQuantity());
        return productRepository.save(savedProduct);
    }

    @Override
    public boolean delete(Integer id) {
        if (productRepository.existsById(id)) {
            productRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
