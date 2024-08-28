package com.example.SpringE_Commerce.Service.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.SpringE_Commerce.Entities.Product;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Long> {

    @Query(value="SELECT * FROM Product p WHERE p.category.CategoryName = :category",nativeQuery=true)
    List<Product> findAllByCategoryName(String category);

    @Query(value = "SELECT * from Product p where p.name = :name",nativeQuery = true)
    Product findByProductName(String name);
}
