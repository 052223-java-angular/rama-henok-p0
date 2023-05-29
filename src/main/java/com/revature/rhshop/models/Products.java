package com.revature.rhshop.models;

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
    private int stock;
    private String category_name; // Should refer to category table

}