package com.pos.posspringbackend.category.service.impl;

import com.pos.posspringbackend.category.entity.Category;
import com.pos.posspringbackend.category.repository.CategoryRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CategoryServiceImplTest {
    @Mock
    private CategoryRepository categoryRepository;
    @InjectMocks
    private CategoryServiceImpl categoryServiceImpl;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    @AfterEach
    void tearDown() throws Exception {
        MockitoAnnotations.openMocks(this).close();
    }
    @Test
    void it_Should_Find_All_Categories() {
        // given
        Category category = Category.builder()
                .categoryName("Sample Category")
                .categoryDesc("Sample Description")
                .build();
        Category category2 = Category.builder()
                .categoryName("Sample Category 2")
                .categoryDesc("Sample Description 2")
                .build();

        // when
        List<Category> categories = new ArrayList<>();
        categories.add(category);
        categories.add(category2);
        when(categoryRepository.findAll()).thenReturn(categories);

        // then
        List<Category> categoriesFromService = categoryServiceImpl.getAllCategories();

        assertEquals(2, categoriesFromService.size());
        assertEquals("Sample Category", categoriesFromService.get(0).getCategoryName());    // check if the first element is the same as the first element of the list
        assertEquals("Sample Description", categoriesFromService.get(0).getCategoryDesc());  // check if the first element is the same as the first element of the list
        assertEquals("Sample Category 2", categoriesFromService.get(1).getCategoryName());  // check if the second element is the same as the second element of the list
        assertEquals("Sample Description 2", categoriesFromService.get(1).getCategoryDesc());  // check if the second element is the same as the second element of the list

        verify(categoryRepository, times(1)).findAll();
    }

    @Test
    void it_Should_Return_A_Saved_Category() {
        // given
        Category category = Category.builder()
                .categoryName("Sample Category")
                .categoryDesc("Sample Description")
                .build();
        // when
        when(categoryRepository.save(category)).thenReturn(category);
        // then
        Category savedCategory = categoryServiceImpl.createCategory(category);
        assertEquals("Sample Category", savedCategory.getCategoryName());
        assertEquals("Sample Description", savedCategory.getCategoryDesc());
        verify(categoryRepository, times(1)).save(category);
    }

    @Test
    void it_Should_Return_A_Category_By_Id() {
        // given
        Category category = Category.builder()
                .categoryName("Sample Category")
                .categoryDesc("Sample Description")
                .build();
        // when
        when(categoryRepository.findById(category.getId())).thenReturn(java.util.Optional.of(category));
        // then
        Category categoryFromService = categoryServiceImpl.findCategoryById(category.getId());
        assertEquals("Sample Category", categoryFromService.getCategoryName());
        assertEquals("Sample Description", categoryFromService.getCategoryDesc());
        verify(categoryRepository, times(1)).findById(category.getId());
    }

    @Test
    void It_Should_Return_A_Updated_Category() {
        // given
        Category category = Category.builder()
                .categoryName("Sample Category")
                .categoryDesc("Sample Description")
                .build();
        Category category2 = Category.builder()
                .categoryName("Sample Category 2")
                .categoryDesc("Sample Description 2")
                .build();
        // when
        when(categoryRepository.findById(category.getId())).thenReturn(java.util.Optional.of(category));
        when(categoryRepository.save(category)).thenReturn(category2);
        // then
        Category updatedCategory = categoryServiceImpl.update(category.getId(), category);
        assertEquals("Sample Category 2", updatedCategory.getCategoryName());
        assertEquals("Sample Description 2", updatedCategory.getCategoryDesc());
        verify(categoryRepository, times(1)).findById(category.getId());
        verify(categoryRepository, times(1)).save(category);
    }

    @Test
    void it_Should_Delete_Category_By_Id() {
       //given
        int id = 1;
        //when
        when(categoryRepository.existsById(id)).thenReturn(true);
        //then
        boolean isDeleted = categoryServiceImpl.delete(id);
        assertTrue(isDeleted);

        verify(categoryRepository, times(1)).existsById(id);
        verify(categoryRepository, times(1)).deleteById(id);
    }

    @Test
    void it_Should_Not_Delete_Category_By_Id() {
        //given
        int id = 1;
        //when
        when(categoryRepository.existsById(id)).thenReturn(false);
        //then
        boolean isDeleted = categoryServiceImpl.delete(id);
        assertFalse(isDeleted);
        verify(categoryRepository, times(1)).existsById(id);
        verify(categoryRepository, never()).deleteById(id);
    }
}