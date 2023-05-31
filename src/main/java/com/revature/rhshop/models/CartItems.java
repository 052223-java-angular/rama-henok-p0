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
    private String cart_item_id;
    private String product_name;
    private float price;
    private int quantity;
    private int cart_id;
    private int product_id;
    private String user_id;

    public CartItems(String product_name, float price, int quantity, int cart_id, int product_id, String user_id)
    {
        this.cart_item_id = UUID.randomUUID().toString();
        this.product_name = product_name;
        this.price = price;
        this.quantity = quantity;
        this.cart_id = cart_id;
        this.product_id = product_id;
        this.user_id = user_id;
    }
}