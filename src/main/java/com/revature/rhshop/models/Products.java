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
    private int productId;
    private String productName;
    private float price;
    private int categoryId;

    public Products(String productName, float price, int categoryId) {
        this.productName = productName;
        this.price = price;
        this.categoryId = categoryId;
    }
}