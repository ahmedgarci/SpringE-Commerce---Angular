package com.example.SpringE_Commerce.Service.Cart;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;

import com.example.SpringE_Commerce.Entities.Cart;
import com.example.SpringE_Commerce.Entities.CartItem;
import com.example.SpringE_Commerce.Entities.Product;
import com.example.SpringE_Commerce.Exceptions.ProductNotFoundException;
import com.example.SpringE_Commerce.Exceptions.ResourceNotFoundException;
import com.example.SpringE_Commerce.Repositories.CartItemReposiotry;
import com.example.SpringE_Commerce.Repositories.CartRepository;
import com.example.SpringE_Commerce.Repositories.ProductRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CartService implements ICartService {
    private final CartRepository cartRepository;
    private final CartItemReposiotry cartItemReposiotry;
    private final ProductRepository productRepository;

    @Override
    public Cart getCart(Long id) {
        Cart cart =  cartRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("cart not found"));
        if(cart == null){
            cart = new Cart();
        }
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
    public void addItemToCart(Long CartId, Long  ProductId ,int quatity) {
        Cart cart = getCart(CartId);
        Product product = productRepository.findById(ProductId)
        .orElseThrow(()->new ProductNotFoundException("product cannot be found"));

        CartItem cartItem = cart.getItems().stream()
        .filter((item)->item.getProduct().getId().equals(product.getId()))
        .findFirst().orElse(new CartItem());

        if(cartItem.getId() == null){
            cartItem.setProduct(product);
            cartItem.setQuantity(quatity);
            cartItem.setUnitPrice(new BigDecimal(product.getPrice()));
        }else{
            cartItem.setQuantity(cartItem.getQuantity() + quatity);
        }
        cart.updateTotal();
        cartItemReposiotry.save(cartItem);
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
