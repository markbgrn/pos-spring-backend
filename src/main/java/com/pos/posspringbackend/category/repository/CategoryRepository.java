package com.pos.posspringbackend.category.repository;

import com.pos.posspringbackend.category.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
