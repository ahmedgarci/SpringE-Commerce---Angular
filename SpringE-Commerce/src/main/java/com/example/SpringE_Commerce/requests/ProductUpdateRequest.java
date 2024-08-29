package com.example.SpringE_Commerce.requests;

import java.util.List;

import com.example.SpringE_Commerce.Entities.Category;
import com.example.SpringE_Commerce.Entities.Image;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductUpdateRequest {
     private Long id;

    private String name;

    private String description;

    private double price;

    private Integer inventory;

    private Category category;

    private List<Image> images;
    
}
