package com.example.SpringE_Commerce.Controller;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import com.example.SpringE_Commerce.Entities.Product;
import com.example.SpringE_Commerce.Responses.ApiResponse;
import com.example.SpringE_Commerce.Service.Product.IProductService;
import com.example.SpringE_Commerce.requests.ProductRequest;
import com.example.SpringE_Commerce.requests.ProductUpdateRequest;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
@RequiredArgsConstructor
public class ProductController {
    private final IProductService productService;

    @PostMapping("/product/add")
    public ResponseEntity<ApiResponse> AddProduct(@RequestBody ProductRequest request) {
        return ResponseEntity.
        ok(new ApiResponse("ajoute avec suucess",productService.addNewProduct(request)));
    }
    
    @GetMapping("/product/{productId}")
    public ResponseEntity<ApiResponse> GetProductByItsId(@PathVariable Long productId) {
        return ResponseEntity.
        ok(new ApiResponse("product  : ",productService.getProductById(productId)));
    }

    @GetMapping("/product/{productId}")
    public ResponseEntity<ApiResponse> DeleteProduct(@PathVariable Long productId) {
        productService.DeleteProductById(productId);
        return ResponseEntity.
        ok(new ApiResponse("deleted ! ",null));
    }

    @PutMapping("/product/{id}/update")
    public ResponseEntity<ApiResponse> UpdateProduct(@PathVariable Long id, 
    @RequestBody ProductUpdateRequest request) {
      return ResponseEntity.
      ok(new ApiResponse("updated ! ", productService.updateProduct(request, id)));
    }
    
    @GetMapping("/product/all")
    public ResponseEntity<List<Product>> getAllProducts() {
        return ResponseEntity.ok(productService.getAllProducts());
    }
    
    @GetMapping("/product/{ProductName}")
    public ResponseEntity<ApiResponse> getMethodName(@RequestParam String ProductName) {
        return ResponseEntity.ok(new ApiResponse(
            "product :", productService.getProductByName(ProductName)));
    }

    // TO do get products by Category
    




}
