package com.pos.posspringbackend.category.service;

import com.pos.posspringbackend.category.entity.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    List<Category> getAllCategories();
    Category createCategory(Category category);
    Category findCategoryById(Integer id);
    Category update(Integer id, Category category);
    boolean delete(Integer id);
}
