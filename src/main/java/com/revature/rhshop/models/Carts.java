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
public class Carts {
    private int cart_id;
    private String user_id;
    

    public Carts(String user_id) {
        this.user_id = user_id;
    }
}