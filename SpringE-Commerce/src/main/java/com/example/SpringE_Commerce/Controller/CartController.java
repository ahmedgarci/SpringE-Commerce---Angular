package com.example.SpringE_Commerce.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.SpringE_Commerce.Exceptions.ProductNotFoundException;
import com.example.SpringE_Commerce.Exceptions.ResourceNotFoundException;
import com.example.SpringE_Commerce.Responses.ApiResponse;
import com.example.SpringE_Commerce.Service.Cart.ICartService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;



@RestController
@RequiredArgsConstructor
@RequestMapping()
public class CartController {
    
    private final ICartService cartService;

    @GetMapping("/cart/{id}")
    public ResponseEntity<ApiResponse> getCart(@PathVariable Long id) throws ResourceNotFoundException {
        return ResponseEntity.ok(new ApiResponse("cart : ",cartService.getCart(id)));
    }

    @DeleteMapping("/cart/{id}")
    public ResponseEntity<ApiResponse> DeleteCart(@PathVariable Long id) throws ResourceNotFoundException {
        cartService.clearCart(id);
        return ResponseEntity.ok(new ApiResponse("deleted ! ",null));
    }

    @PostMapping("/cart/add")
    public ResponseEntity<ApiResponse> addNewItemIntoCart(@RequestParam Long CartId,
    @RequestParam  Long ProductId,
    @RequestParam  Integer quantity
    ) throws ProductNotFoundException {
       cartService.addItemToCart(CartId, ProductId, quantity); 
        return ResponseEntity.ok(new ApiResponse("added",null));
    }
    
    @DeleteMapping
    public ResponseEntity<ApiResponse> deleteItemFromCart(@PathVariable Long CartId,
    @PathVariable  Long itemId
    ) throws ProductNotFoundException {
       cartService.removeItemFromCart(CartId, itemId);
        return ResponseEntity.ok(new ApiResponse("item deleted from cart",null));
    }

    


    
}
