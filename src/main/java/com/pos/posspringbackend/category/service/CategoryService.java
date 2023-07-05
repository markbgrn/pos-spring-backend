package com.pos.posspringbackend.category.service;

import com.pos.posspringbackend.category.entity.Category;

import java.util.List;

public interface CategoryService {
    List<Category> getAllCategories();
    Category create(Category category);
    Category findById(Long id);
    Category update(Long id, Category category);
    boolean delete(Long id);
}
