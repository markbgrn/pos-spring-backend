package com.pos.posspringbackend.category.repository;

import com.pos.posspringbackend.category.document.CategoryDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CategoryMongoRepository extends MongoRepository<CategoryDocument, String> {
}
