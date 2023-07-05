package com.pos.posspringbackend.category.controller;

import com.pos.posspringbackend.category.entity.Category;
import com.pos.posspringbackend.category.service.CategoryMongoService;
import com.pos.posspringbackend.category.service.CategoryService;
import com.pos.posspringbackend.category.document.CategoryDocument;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/api/v1/category")
@CrossOrigin(origins = "localhost:4200")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;
    private final CategoryMongoService categoryMongoService;

    @GetMapping
    public ResponseEntity<List<Category>> getAllCategories() {
        List<Category> categories = categoryService.getAllCategories();
        if (categories.isEmpty()) {
            return new ResponseEntity<>(NO_CONTENT);
        }
        return new ResponseEntity<>(categories, OK);
    }
    @GetMapping("/categories")
    public ResponseEntity<List<CategoryDocument>> getAllCategoriesInMongoDb() {
        List<CategoryDocument> categories = categoryMongoService.getAllCategories();
        return new ResponseEntity<>(categories, OK);
    }
    @PostMapping("/create")
    public ResponseEntity<Category> create(@RequestBody Category category) {
        Category savedCategory = categoryService.create(category);
        return new ResponseEntity<>(savedCategory, CREATED);
    }

    @PostMapping("/save")
    public ResponseEntity<CategoryDocument> create(@RequestBody CategoryDocument CategoryDocument) {
        CategoryDocument savedCategory = categoryMongoService.create(CategoryDocument);
        return new ResponseEntity<>(savedCategory, CREATED);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Category> findById(@PathVariable Long id) {
        Category category = categoryService.findById(id);
        return new ResponseEntity<>(category, OK);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Category> update(@PathVariable Long id, @RequestBody Category category) {
        Category updatedCategory = categoryService.update(id, category);
        return new ResponseEntity<>(updatedCategory, OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable Long id) {
        boolean isDeleted = categoryService.delete(id);
        return new ResponseEntity<>(isDeleted, OK);
    }
}
