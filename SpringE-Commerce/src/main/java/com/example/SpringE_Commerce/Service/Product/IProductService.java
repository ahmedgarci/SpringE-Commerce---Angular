package com.example.SpringE_Commerce.Service.Product;
import java.util.List;

import com.example.SpringE_Commerce.Entities.Category;
import com.example.SpringE_Commerce.Entities.Product;
import com.example.SpringE_Commerce.requests.ProductRequest;
import com.example.SpringE_Commerce.requests.ProductUpdateRequest;

public interface IProductService {
    Product addNewProduct(ProductRequest product);
    Product getProductById(Long id);
    void DeleteProductById(Long id);
    Product updateProduct(ProductUpdateRequest product,Long productId);
    List<Product> getAllProducts();
    List<Product> getAllProductsByCategory(Category Category);
    Product getProductByName(String name);
}
