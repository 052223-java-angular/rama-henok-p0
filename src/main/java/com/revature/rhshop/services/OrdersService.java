package com.revature.rhshop.services;

import java.util.List;

import com.revature.rhshop.daos.OrderDAO;
import com.revature.rhshop.models.OrderItems;
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

    public List<Orders> findAll(String user_id) {
        return orderDAO.findAllByUser(user_id);
        }


    public List<OrderItems> findAllpurchasedItemsFinderByUser(int order_id) {
        return orderDAO.findAllpurchasedItemsFinderByUser(order_id);
    }

    public Orders purchasedOrders(String user_id) {
        return orderDAO.findAllByUserId(user_id);
    }
}
