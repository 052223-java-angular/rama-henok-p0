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

    public List<CartItems> findAll() {
        
        
        List<CartItems> l = cartItemsDAO.findAll();
        
        return cartItemsDAO.findAll();
    }

    public void addToCart( String userId ){
        Carts cart = new Carts(userId);
        cartDAO.save(cart);
    }

    public void addToCartItems( String userId, int productId, String productName, float productPrice, int stock  ){
       Carts cart = cartDAO.findById(userId);

       CartItems cartItems = new CartItems( productName, productPrice, stock, cart.getCart_id(), productId );

       cartItemsDAO.save( cartItems  );
    }

   




    
}
