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

public class Carts {
    private int cartId;
    private String userId;
    

    public Carts(String userId) {
        this.userId = userId;
    }
}