package com.example.SpringE_Commerce.Service.Cart;

import com.example.SpringE_Commerce.Entities.Cart;
import com.example.SpringE_Commerce.Entities.CartItem;

public interface ICartService {
    Cart getCart(Long id);
    void clearCart(Long id);
    void addItemToCart(Long CartId, CartItem item,int quatity);
    void removeItemFromCart(Long cart_id,Long itemId);
} 
