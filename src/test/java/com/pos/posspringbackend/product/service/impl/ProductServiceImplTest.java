package com.pos.posspringbackend.product.service.impl;

import com.pos.posspringbackend.category.entity.Category;
import com.pos.posspringbackend.category.repository.CategoryRepository;
import com.pos.posspringbackend.product.entity.Product;
import com.pos.posspringbackend.product.repository.ProductRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProductServiceImplTest {
    @Mock
    private ProductRepository productRepository;
    @Mock
    private CategoryRepository categoryRepository;
    @InjectMocks
    private ProductServiceImpl productServiceImpl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void tearDown() throws Exception {
        MockitoAnnotations.openMocks(this).close();
    }

    @Test
    void it_Should_Return_All_Of_Products() {
        // given
        String categoryName = "Sample Category";
        Category category = new Category();
        category.setCategoryName(categoryName);
        category.setCategoryDesc("Sample Description");

        Product product = Product.builder()
                .prodName("Sample Product")
                .prodDesc("Sample Description")
                .category(category)
                .unitPrice(100.00)
                .quantity(10)
                .build();
        Product product2 = Product.builder()
                .prodName("Sample Product 2")
                .prodDesc("Sample Description 2")
                .category(category)
                .unitPrice(200.00)
                .quantity(20)
                .build();
        // when
        when(productRepository.findAll()).thenReturn(List.of(product, product2));
        // then
        List<Product> products = productServiceImpl.getAllProducts();
        assertEquals(2, products.size());
        assertEquals("Sample Product", products.get(0).getProdName());
        assertEquals("Sample Description", products.get(0).getProdDesc());
        assertEquals("Sample Product 2", products.get(1).getProdName());
        assertEquals("Sample Description 2", products.get(1).getProdDesc());

        verify(productRepository, times(1)).findAll();

    }

    @Test
    void it_Should_Create_A_Product_With_Category() {
        //given
        String categoryName = "Sample Category";
        Category category = new Category();
        category.setCategoryName(categoryName);
        category.setCategoryDesc("Sample Description");

        Product product = Product.builder()
                .prodName("Sample Product")
                .prodDesc("Sample Description")
                .category(category)
                .unitPrice(100.00)
                .quantity(10)
                .build();
        //when
        when(productRepository.save(product)).thenReturn(product);
        when(categoryRepository.findById(category.getId())).thenReturn(Optional.of(category));
        //then
        Product savedProduct = productServiceImpl.createProduct(product);
        assertEquals("Sample Product", savedProduct.getProdName());
        assertEquals("Sample Description", savedProduct.getProdDesc());
        assertEquals("Sample Category", savedProduct.getCategory().getCategoryName());
        assertEquals(100.00, savedProduct.getUnitPrice());
        assertEquals(10, savedProduct.getQuantity());

        verify(productRepository, times(1)).save(product);
    }

    @Test
    void it_Should_Find_Product_By_Id() {
        // given
        String CategoryName = "Sample Category";
        Category category = new Category();
        category.setCategoryName(CategoryName);
        category.setCategoryDesc("Sample Description");

        Product product = Product.builder()
                .prodName("Sample Product")
                .prodDesc("Sample Description")
                .unitPrice(100.00)
                .category(category)
                .quantity(10)
                .build();
        // when
        when(productRepository.findById(1)).thenReturn(java.util.Optional.of(product));
        // then
        Product foundProduct = productServiceImpl.findById(1);
        assertEquals("Sample Product", foundProduct.getProdName());
        assertEquals("Sample Description", foundProduct.getProdDesc());
        assertEquals("Sample Category", foundProduct.getCategory().getCategoryName());
        assertEquals(100.00, foundProduct.getUnitPrice());
        assertEquals(10, foundProduct.getQuantity());

        verify(productRepository, times(1)).findById(1);
    }

    @Test
    void it_Should_Update_A_Product() {
        // given
        String categoryName = "Sample Category";
        Category category = new Category();
        category.setCategoryName(categoryName);
        category.setCategoryDesc("Sample Description");

        Product product = Product.builder()
                .prodName("Sample Product")
                .prodDesc("Sample Description")
                .category(category)
                .unitPrice(100.00)
                .quantity(10)
                .build();

        Product product2 = Product.builder()
                .prodName("Sample Product 2")
                .prodDesc("Sample Description 2")
                .category(category)
                .unitPrice(200.00)
                .quantity(20)
                .build();
        // when
        when(productRepository.findById(1)).thenReturn(java.util.Optional.of(product));
        when(productRepository.save(product)).thenReturn(product2);
        // then
        Product savedProduct = productServiceImpl.update(1, product);
        assertEquals("Sample Product 2", savedProduct.getProdName());
        assertEquals("Sample Description 2", savedProduct.getProdDesc());
        assertEquals(200.00, savedProduct.getUnitPrice());
        assertEquals(20, savedProduct.getQuantity());

        verify(productRepository, times(1)).findById(1);
        verify(productRepository, times(1)).save(product);
    }

    @Test
    void it_Should_Delete_The_Product_By_Id() {
        // given
        int id = 1;
        // when
        when(productRepository.existsById(id)).thenReturn(true);
        // then
        boolean isDeleted = productServiceImpl.delete(id);
        assertTrue(isDeleted);
        verify(productRepository, times(1)).existsById(1);
        verify(productRepository, times(1)).deleteById(1);
    }

    @Test
    void it_Should_Not_Delete_The_Product_By_Id() {
        // given
        int id = 1;
        // when
        when(productRepository.existsById(id)).thenReturn(false);
        // then
        boolean isDeleted = productServiceImpl.delete(1);
        assertFalse(isDeleted);
        verify(productRepository, times(1)).existsById(1);
        verify(productRepository, never()).deleteById(1);
    }
}