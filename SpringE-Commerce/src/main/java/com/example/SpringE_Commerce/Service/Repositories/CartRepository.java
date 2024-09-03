package com.example.SpringE_Commerce.Service.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.SpringE_Commerce.Entities.Cart;

public interface CartRepository extends JpaRepository<Cart,Long>{
    
}
