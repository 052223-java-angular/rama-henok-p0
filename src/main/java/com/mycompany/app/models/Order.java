package com.mycompany.app.models;


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


public class Order {
    private String order_id;
    private String user_id;
    private String product_id;
    private int quantity;
    private double unit_price;
}
