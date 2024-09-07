package com.example.SpringE_Commerce.Service.Cart;

import com.example.SpringE_Commerce.Entities.Cart;

public interface ICartService {
    Cart getCart(Long id);
    void clearCart(Long id);
    void addItemToCart(Long CartId,Long ProductId,int quatity);
    void removeItemFromCart(Long cart_id,Long itemId);
} 
