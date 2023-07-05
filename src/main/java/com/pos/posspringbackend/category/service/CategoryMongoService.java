package com.pos.posspringbackend.category.service;



import com.pos.posspringbackend.category.document.CategoryDocument;

import java.util.List;

public interface CategoryMongoService {
    List<CategoryDocument> getAllCategories();
    CategoryDocument create(CategoryDocument categoryDocument);
    CategoryDocument findById(Long id);
    CategoryDocument update(Long id, CategoryDocument categoryDocument);
    boolean delete(Long id);
}
