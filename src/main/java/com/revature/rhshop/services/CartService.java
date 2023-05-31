package com.revature.rhshop.services;

import java.util.List;

import com.revature.rhshop.daos.CartItemsDAO;
import com.revature.rhshop.daos.CartDAO;
import com.revature.rhshop.models.CartItems;
import com.revature.rhshop.models.Carts;
import com.revature.rhshop.utils.Session;

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

    public int updateQuantity(String cart_item_id, int quantity, String user_id){

        return cartItemsDAO.updateQuantity(cart_item_id, quantity, user_id);

    }

    public boolean delete(String cart_item_id, String user_id) {

        return cartItemsDAO.deleteById(cart_item_id, user_id);

    }

    public String findByProductName(String user_id, String product_name){

        return cartItemsDAO.findByProductName(user_id, product_name);

    }


    public List<CartItems> findAll(String user_id) {
        
        return cartItemsDAO.findAllByUser(user_id);
    }

    public Carts addToCart( String userId ){
        Carts cart = new Carts(userId);
        return cartDAO.saveCart(cart);
    }

    public void addToCartItems(CartItems cartItems){
       cartItemsDAO.save(cartItems);
    }

    public double totalPriceCalculator(String user_id) {

        return cartItemsDAO.priceCalculator(user_id);

    
    }

    public void celarCart() {
        cartItemsDAO.celarCart();
    }

   




    
}