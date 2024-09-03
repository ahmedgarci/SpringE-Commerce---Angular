package com.example.SpringE_Commerce.Service.Cart;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.SpringE_Commerce.Entities.Cart;
import com.example.SpringE_Commerce.Entities.CartItem;
import com.example.SpringE_Commerce.Exceptions.ResourceNotFoundException;
import com.example.SpringE_Commerce.Service.Repositories.CartItemReposiotry;
import com.example.SpringE_Commerce.Service.Repositories.CartRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CartService implements ICartService {
    private final CartRepository cartRepository;
    private final CartItemReposiotry cartItemReposiotry;


    @Override
    public Cart getCart(Long id) {
        Cart cart =  cartRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("cart not found"));
        cart.updateTotal();
        return cart;
    }

    @Override
    public void clearCart(Long id) {
        Cart cart =  cartRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("cart not found"));
        
        cart.getItems().clear();
        cart.updateTotal();
        
        cartRepository.save(cart);        
    }

    @Override
    public void addItemToCart(Long CartId, CartItem item) {
        Cart cart = getCart(CartId);
        item.setCart(cart);
        
        cart.getItems().add(item);
        cart.updateTotal();
     
        cartItemReposiotry.save(item);
        cartRepository.save(cart);
    }

    @Override
    public void removeItemFromCart(Long cart_id, Long itemId) {
        Cart cart = getCart(cart_id);
        CartItem item = cartItemReposiotry.findById(itemId)
           .orElseThrow(()->new ResourceNotFoundException("item not found"));

        cart.getItems().remove(item);
        cart.updateTotal();
        
        cartItemReposiotry.delete(item);
        cartRepository.save(cart);
    }
    

 
    


}
