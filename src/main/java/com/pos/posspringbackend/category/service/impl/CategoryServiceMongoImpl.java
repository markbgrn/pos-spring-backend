package com.pos.posspringbackend.category.service.impl;

import com.pos.posspringbackend.category.document.CategoryDocument;
import com.pos.posspringbackend.category.repository.CategoryMongoRepository;
import com.pos.posspringbackend.category.service.CategoryMongoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class CategoryServiceMongoImpl implements CategoryMongoService {
    private final CategoryMongoRepository categoryMongoRepository;

    @Override
    public List<CategoryDocument> getAllCategories() {
        return categoryMongoRepository.findAll();
    }

    @Override
    public CategoryDocument create(CategoryDocument categoryDocument) {
        CategoryDocument savedCategoryDocument = CategoryDocument.builder()
                .name(categoryDocument.getName())
                .description(categoryDocument.getDescription())
                .build();
        return categoryMongoRepository.save(savedCategoryDocument);
    }

    @Override
    public CategoryDocument findById(Long id) {
        return null;
    }

    @Override
    public CategoryDocument update(Long id, CategoryDocument categoryDocument) {
        return null;
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }
}
