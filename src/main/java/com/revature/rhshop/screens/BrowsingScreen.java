package com.revature.rhshop.screens;

import com.revature.rhshop.models.CartItems;
import com.revature.rhshop.models.Carts;
import com.revature.rhshop.models.Products;
import com.revature.rhshop.utils.Session;

import java.util.List;
import java.util.Scanner;
import java.util.UUID;

import com.revature.rhshop.services.RouterService;
import com.revature.rhshop.services.ProductService;
import com.revature.rhshop.services.CartService;
import org.apache.logging.log4j.LogManager;

//import com.revature.rhshop.services.ProductService;
import org.apache.logging.log4j.Logger;
import lombok.AllArgsConstructor;

//@AllArgsConstructor
public class BrowsingScreen implements IScreen{
    private final ProductService productService;
    private Session session;
    RouterService router;
    private final CartService cartService;
    
    
    public BrowsingScreen(ProductService productService, RouterService routerService, Session session, CartService cartService) {
        this.productService = productService;
        this.router = routerService;
        this.session = session;
        this.cartService = cartService;
    }


    private static final Logger logger = LogManager.getLogger(BrowsingScreen.class);
    @Override
        public void start(Scanner scan){
        String input = "";
        System.out.println("browsing shop");
        logger.info("Navigated to Browsing Screen");
         
        exit: {
            while (true) {
                List<Products> productList = productService.getAll();
                clearScreen();
                System.out.println("Welcome to the product screen, " + session.getUsername() + " !");
                System.out.println("Please select an item below: \n");

                // loop over productList and print each product
                for (int i = 0; i < productList.size(); i++) {
                    Products product = productList.get(i);
                    // Assuming the Product class has a toString() method to print details of the product
                    int index = i+1;
                    System.out.println("ItemNum: " + index + "      ProductName: "  + product.getProduct_name() + "       Price: " + 
                    product.getPrice() + "       Stock: " + product.getStock());

                }
                
                System.out.println("\n[x] Exit");
                System.out.print("\nAdd To Cart- Enter Item Number: ");
                input = scan.nextLine();
                
                try{ 
                    int productIndex = Integer.parseInt(input) -1; 
                    if(productIndex >= 0 && productIndex < productList.size()){
                        Products product = productList.get(productIndex);
                        System.out.println("You selected: " + product.getProduct_name());
                        //fix
                        String productName = product.getProduct_name();
                        String cart_item_id = searchCartByName(productName);
                       
                        if(!cart_item_id.equals("")){

                            CartItems cartitem = this.cartService.findById(cart_item_id);
                            int quantity = cartitem.getQuantity() + 1;

                            this.cartService.updateQuantity(cart_item_id, quantity);
                        }else{
                        int quantity = 1;
                        
                        Carts cart = addToCart(product);

                        System.out.println(addCartItem(product, quantity, cart.getCart_id()));
                        input = scan.nextLine();
                        }

                    } else{
                        logger.warn("Invalid option!");
                        clearScreen();
                    }
                } catch(NumberFormatException e){
                    switch (input) {
                        case "x":
                            logger.info("User signed out");
                            break exit;
                        default:
                            logger.warn("Invalid option!");
                            clearScreen();
                     }
                }
            }
                
        } 
    }

    private void clearScreen() {
    System.out.print("\033[H\033[2J");
    System.out.flush();
    }

    private Carts addToCart(Products product ) {
        return this.cartService.addToCart(session.getId());
    }

    private String addCartItem(Products product, int quantity, int cart_id) {
        CartItems cartItems = new CartItems();

        cartItems.setCart_item_id(UUID.randomUUID().toString());
        cartItems.setProduct_name(product.getProduct_name());
        cartItems.setPrice(product.getPrice());
        cartItems.setQuantity(quantity);
        cartItems.setCart_id(cart_id);
        cartItems.setProduct_id(product.getProduct_id());

        cartService.addToCartItems(cartItems);

        return "Added to cart successfully....";
    }

    private String searchCartByName(String product_name){

        return this.cartService.findByProductName(product_name);
        
    }
 
}