package com.example.SpringE_Commerce.Service.Product;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.SpringE_Commerce.Entities.Category;
import com.example.SpringE_Commerce.Entities.Product;
import com.example.SpringE_Commerce.Exceptions.ProductNotFoundException;
import com.example.SpringE_Commerce.Service.Repositories.ProductRepository;
import com.example.SpringE_Commerce.requests.ProductRequest;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductService implements IProductService {

    private final ProductRepository productRepository;


    @Override
    public Product addNewProduct(ProductRequest request) {
      Product p =   new Product();
        p.builder()
        .name(request.getName())
        .description(request.getDescription())
        .price(request.getPrice()).category(request.getCategory())
        //.images(null)
        .build();
        productRepository.save(p);
        return p;
    }

    @Override
    public Product getProductById(Long id) {
        return  
        productRepository.findById(id)
        .orElseThrow(()-> new ProductNotFoundException("product not found with id : "+id));        
    }

    @Override
    public void DeleteProductById(Long id) {
        productRepository.findById(id).ifPresentOrElse(productRepository::delete, ()->new ProductNotFoundException("product not found with id : "+id));
    }

    @Override
    public void updateProduct(Product product, Long productId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateProduct'");
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public List<Product> getAllProductsByCategory(Category Category) {
        return productRepository.findAllByCategoryName(Category.getCategoryName());
    }

    @Override
    public Product getProductByName(String name) {
        return productRepository.findByProductName(name);
    }

    
}
