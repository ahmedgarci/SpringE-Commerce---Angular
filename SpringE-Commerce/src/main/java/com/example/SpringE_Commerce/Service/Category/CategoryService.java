package com.example.SpringE_Commerce.Service.Category;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.SpringE_Commerce.Entities.Category;
import com.example.SpringE_Commerce.Exceptions.AlreadyExistsException;
import com.example.SpringE_Commerce.Exceptions.ResourceNotFoundException;
import com.example.SpringE_Commerce.Service.Repositories.CategoryRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoryService implements ICategoryService {

    private final CategoryRepository categoryRepository;


    @Override
    public Category getCategoryById(Long id) {
        return categoryRepository.findById(id)
        .orElseThrow(()-> new ResourceNotFoundException("Category not found"));
    }

    @Override
    public Category getCategoryByName(String name) {
        return categoryRepository.findByCategoryName(name);
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Category addCategory(Category category) {
        return Optional.ofNullable(category)
            .filter(c -> !categoryRepository.existsByName(c.getCategoryName()))
            .map(categoryRepository::save)
            .orElseThrow(() -> new AlreadyExistsException("Category already exists"));
    }

    @Override
    public Category UpdateCategory(Category category, Long id) {
        return Optional.ofNullable(getCategoryById(id))
        .map(oldCategory -> {
            oldCategory.setCategoryName(category.getCategoryName());
            return categoryRepository.save(oldCategory);
        }).orElseThrow(()-> new ResourceNotFoundException("Category not found"));
    }

    @Override
    public void deleteCategoryById(Long id) {
        categoryRepository.findById(id).ifPresent(categoryRepository:: delete);
    }
    
}
