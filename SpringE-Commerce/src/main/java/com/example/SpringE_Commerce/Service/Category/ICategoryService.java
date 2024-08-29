package com.example.SpringE_Commerce.Service.Category;

import com.example.SpringE_Commerce.Entities.Category;
import java.util.List;
public interface ICategoryService{
    
    Category getCategoryById(Long id);
    Category getCategoryByName(String name);
    List<Category> getAllCategories();
    Category addCategory(Category category);
    Category UpdateCategory(Category category,Long id);
    void deleteCategoryById(Long id);


}