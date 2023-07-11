package com.pos.posspringbackend.category.service.impl;

import com.pos.posspringbackend.category.entity.Category;
import com.pos.posspringbackend.utils.exception.ResourceNotFoundException;
import com.pos.posspringbackend.category.repository.CategoryRepository;
import com.pos.posspringbackend.category.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    @Transactional
    public Category create(Category category) {
        Category savedCategory = Category.builder()
                .categoryName(category.getCategoryName())
                .categoryDesc(category.getCategoryDesc())
                .build();
        return categoryRepository.save(savedCategory);
    }

    @Override
    public Category findById(Integer id) {
        Optional<Category> category = categoryRepository.findById(id.longValue());
        return category.orElseThrow(() -> new ResourceNotFoundException("Category with id: " + id + " not found"));
    }

    @Override
    public Category update(Integer id, Category category) {
        Category savedCategory = categoryRepository.findById(id.longValue()).orElseThrow(() -> new ResourceNotFoundException("Category with id: " + id + " not found"));
        savedCategory.setCategoryName(category.getCategoryName());
        savedCategory.setCategoryDesc(category.getCategoryDesc());
        return categoryRepository.save(savedCategory);
    }

    @Override
    public boolean delete(Integer id) {
        if (categoryRepository.existsById(id.longValue())) {
            categoryRepository.deleteById(id.longValue());
            return true;
        }
        return false;
    }
}
