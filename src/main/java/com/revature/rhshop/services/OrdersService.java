package com.revature.rhshop.services;

import com.revature.rhshop.daos.OrderDAO;
import com.revature.rhshop.models.Orders;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class OrdersService {
    private final OrderDAO orderDAO;

    public boolean movingCartItems(Orders orders){

        if(orderDAO.movingCartItems(orders)){
            return true;
        }

        return false;

    }
    
}
