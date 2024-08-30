package com.example.SpringE_Commerce.Service.Product;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.SpringE_Commerce.Entities.Category;
import com.example.SpringE_Commerce.Entities.Product;
import com.example.SpringE_Commerce.Exceptions.ProductNotFoundException;
import com.example.SpringE_Commerce.Service.Repositories.CategoryRepository;
import com.example.SpringE_Commerce.Service.Repositories.ProductRepository;
import com.example.SpringE_Commerce.requests.ProductRequest;
import com.example.SpringE_Commerce.requests.ProductUpdateRequest;

import java.util.Optional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductService implements IProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    @Override
    public Product addNewProduct(ProductRequest request) {
        Category category = Optional.ofNullable(categoryRepository.findByCategoryName(request.getCategory().getCategoryName()))
        .orElseGet(()-> {
            Category c = new Category(request.getCategory().getCategoryName());
            return categoryRepository.save(c);
        });
        Product p =   new Product();
        p.builder()
        .name(request.getName())
        .description(request.getDescription())
        .price(request.getPrice()).category(category)
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
    public Product updateProduct(ProductUpdateRequest requestProduct, Long productId) {
        Product existingProduct = productRepository.findById(productId).orElseThrow(()-> new ProductNotFoundException("product not found"));
        return updateExistingProduct(existingProduct,requestProduct);

    }



    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public List<Product> getAllProductsByCategory(Category Category) {
        return productRepository.findAllByCategory_CategoryName(Category.getCategoryName());
    }

    @Override
    public Product getProductByName(String name) {
        return productRepository.findByName(name);
    }

    private Product updateExistingProduct(Product existingProduct, ProductUpdateRequest request) {
        existingProduct.setCategory(request.getCategory());
        existingProduct.setDescription(request.getDescription());
        existingProduct.setInventory(request.getInventory());
        existingProduct.setName(request.getName());
        existingProduct.setPrice(request.getPrice());
    
        return productRepository.save(existingProduct);
    }
        

}
    



