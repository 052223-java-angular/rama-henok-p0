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


public class Product {
    private String product_id;
    private String product_name;
    private double price;
    private String category;
}
