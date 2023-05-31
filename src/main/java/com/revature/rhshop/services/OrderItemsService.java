package com.revature.rhshop.services;

import com.revature.rhshop.daos.OrderItemsDAO;
import com.revature.rhshop.models.CartItems;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class OrderItemsService {
    private final OrderItemsDAO orderItemsDAO;

    public boolean movingCartItems(CartItems orders){

        if(orderItemsDAO.movingCartItems(orders)){
            return true;
        }

        return false;

    }
    
}
