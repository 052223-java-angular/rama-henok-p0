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
public class Products {
    private int product_id;
    private String product_name;
    private float price;
    private int category_id;
    private int stock;
    
    /* 

    public Products(String productName, float price, int categoryId) {
        this.productName = productName;
        this.price = price;
        this.categoryId = categoryId;
    }
    */
}