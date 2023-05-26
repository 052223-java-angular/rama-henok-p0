package com.revature.rhshop.models;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class CartItems {
    private int cartItemId;
    private String productName;
    private float price;
    private int quantity;
    private int cartId;
    private int productId;

    public CartItems( 
        String productName,  
        float price, int quantity, int cartId, int productId )
    {
        this.productName = productName;
        this.price = price;
        this.quantity = quantity;
        this.cartId = cartId;
        this.productId = productId;
    }
}