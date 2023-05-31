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
public class Orders {
    private int order_id;
    private String product_name;
    private double total_cost;
    private String order_time;
    private String user_id;
   

}