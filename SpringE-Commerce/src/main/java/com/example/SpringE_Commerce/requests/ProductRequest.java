package com.example.SpringE_Commerce.requests;

import com.example.SpringE_Commerce.Entities.Category;

import lombok.Data;

@Data
public class ProductRequest {

    private String name;

    private String description;

    private double price;

    private Integer inventory;

    private Category category;


}
