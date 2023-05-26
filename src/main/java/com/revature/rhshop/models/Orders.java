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
public class Orders {
    private int orderId;
    private String productName;
    private float totalCost;
    private String orderTime;
    private String userId;
   

    public Orders(String productName, float totalCost, String userId, String time) {
        this.productName = productName;
        this.totalCost = totalCost;
        this.userId = userId;
        this.orderTime = time;
    }
}