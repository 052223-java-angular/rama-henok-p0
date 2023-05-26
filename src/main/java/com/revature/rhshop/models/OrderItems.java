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

public class OrderItems {
    private int orderItemId;
    private int quantity;
    private float price;
    private int orderId;
    private int productId;
   

    public OrderItems(int quantity, float price, int orderId, int productId) {
        this.quantity = quantity;
        this.price = price;
        this.orderId = orderId;
        this.productId = productId;
    }
}