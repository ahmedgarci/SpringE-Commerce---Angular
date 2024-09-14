package com.example.SpringE_Commerce.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.SpringE_Commerce.Entities.Category;

public interface CategoryRepository extends JpaRepository<Category,Long> {
    
    Category findByCategoryName(String categoryName);
    
    boolean existsByCategoryName(String categoryName);

    
}
