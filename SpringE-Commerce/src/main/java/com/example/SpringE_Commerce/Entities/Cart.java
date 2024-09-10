
package com.example.SpringE_Commerce.Entities;

import java.math.BigDecimal;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Cart{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BigDecimal total = BigDecimal.ZERO;

    @OneToMany(mappedBy = "cart",cascade = CascadeType.ALL,orphanRemoval = true)
    private Set<CartItem> items;

    @OneToOne
    @JoinColumn(name = "order_id",referencedColumnName = "id")
    private Order order;    

    public void updateTotal(){
        this.total = items.stream()
            .map(CartItem::getTotalPrice)
            .reduce(BigDecimal.ZERO,BigDecimal::add);
    }


}
