package com.example.SpringE_Commerce.Entities;

import java.math.BigDecimal;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class CartItem{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne()
    @JoinColumn(name = "product_id",nullable = false)
    private Product product;

    private Integer quantity;

    private BigDecimal unitPrice;

    private BigDecimal totalPrice;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cart_id",nullable = false)
    private Cart cart;

    public BigDecimal getTotalPrice(){
        return CalculateTotalPrice();
    }
    
    public void updateTotalPrice(){
        this.totalPrice = CalculateTotalPrice();
    }

    private BigDecimal CalculateTotalPrice(){
        return unitPrice.multiply(new BigDecimal(quantity));
    }



}
