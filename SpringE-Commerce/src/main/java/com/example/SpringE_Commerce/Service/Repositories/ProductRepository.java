package com.example.SpringE_Commerce.Service.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.SpringE_Commerce.Entities.Product;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Long> {

    List<Product> findAllByCategoryName(String category);

    Product findByProductName(String name);
}
