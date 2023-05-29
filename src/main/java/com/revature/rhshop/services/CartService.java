package com.revature.rhshop.services;

import java.util.List;

import com.revature.rhshop.daos.CartItemsDAO;
import com.revature.rhshop.daos.CartDAO;
import com.revature.rhshop.models.CartItems;
import com.revature.rhshop.models.Carts;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CartService {
    private final CartItemsDAO cartItemsDAO;
    private final CartDAO cartDAO;


    public CartItems findById(String cart_item_id){

        CartItems cartOpt = cartItemsDAO.findById(cart_item_id); 
        System.out.println(cartOpt);

        return cartOpt;
    }

    public int updateQuantity(String cart_item_id, int quantity){

        return cartItemsDAO.updateQuantity(cart_item_id, quantity);

    }

    public boolean delete(String cart_item_id) {

        return cartItemsDAO.deleteById(cart_item_id);

    }

    public String findByProductName(String product_name){

        return cartItemsDAO.findByProductName(product_name);

    }


    public List<CartItems> findAll() {
        
        return cartItemsDAO.findAll();
    }

    public Carts addToCart( String userId ){
        Carts cart = new Carts(userId);
        return cartDAO.saveCart(cart);
    }

    public void addToCartItems(CartItems cartItems){
       cartItemsDAO.save(cartItems);
    }

   




    
}
