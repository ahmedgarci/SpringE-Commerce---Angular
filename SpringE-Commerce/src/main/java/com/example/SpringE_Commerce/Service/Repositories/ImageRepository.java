package com.example.SpringE_Commerce.Service.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.SpringE_Commerce.Entities.Image;

public interface ImageRepository extends JpaRepository<Image,Long> {
    
}
