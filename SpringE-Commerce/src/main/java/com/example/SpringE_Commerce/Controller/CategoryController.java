package com.example.SpringE_Commerce.Controller;

import org.springframework.web.bind.annotation.RestController;

import com.example.SpringE_Commerce.Entities.Category;
import com.example.SpringE_Commerce.Exceptions.AlreadyExistsException;
import com.example.SpringE_Commerce.Exceptions.ResourceNotFoundException;
import com.example.SpringE_Commerce.Responses.ApiResponse;
import com.example.SpringE_Commerce.Service.Category.CategoryService;

import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;




@RestController
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService ;

    @GetMapping("/category/{categoryId}")
    public ResponseEntity<Category> getCategory(@PathVariable Long categoryId)throws ResourceNotFoundException {
        return ResponseEntity.ok(categoryService.getCategoryById(categoryId));
    }

    @GetMapping("/category/all")
    public ResponseEntity<List<Category>> getAllCategories()throws ResourceNotFoundException {
        return ResponseEntity.ok(categoryService.getAllCategories());
    }

    @PostMapping("/category")
    public ResponseEntity<ApiResponse> createCategory(@RequestBody Category request) {
        try {
            Category category = categoryService.addCategory(request);
            return ResponseEntity.ok(new ApiResponse("added successfully",category));

        } catch (AlreadyExistsException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new ApiResponse(e.getMessage(), null));
        }
    }
    
    @DeleteMapping("/category/{id}/delete")
    public ResponseEntity<ApiResponse> deleteCategory(@PathVariable Long id) {
        try {
            categoryService.deleteCategoryById(id);
            return ResponseEntity.ok(new ApiResponse("deleted successfully",null));

        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @PutMapping("category/{id}/update")
    public ResponseEntity<ApiResponse> update(@PathVariable Long id,@RequestBody Category category) {
        try {
            Category updatedCategory = categoryService.UpdateCategory(category, id);
            return ResponseEntity.ok(new ApiResponse("deleted successfully",updatedCategory));

        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new ApiResponse(e.getMessage(), null));
        }
    }



    
    
}
