package com.revature.rhshop.services;

import java.util.Optional;

import com.revature.rhshop.daos.CartItemsDAO;
import com.revature.rhshop.models.CartItems;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CartService {
    private final CartItemsDAO cartItemsDAO;

    public CartItems findById(String cart_item_id){

        CartItems cartOpt = cartItemsDAO.findById(cart_item_id); 
        System.out.println(cartOpt);

        return cartOpt;
    }

    public int updateQuantity(String cart_item_id, int quantity){

        return cartItemsDAO.updateQuantity(cart_item_id, quantity);

    }




    
}
