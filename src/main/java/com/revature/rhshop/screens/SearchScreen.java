package com.revature.rhshop.screens;


import java.util.logging.LogManager;
import java.util.logging.Logger;

import lombok.AllArgsConstructor;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import com.revature.rhshop.models.Products;
import com.revature.rhshop.utils.Session;
import com.revature.rhshop.services.ProductService;
import com.revature.rhshop.services.RouterService;
import com.revature.rhshop.models.CartItems;
import com.revature.rhshop.models.Carts;
import com.revature.rhshop.services.CartService;
import java.util.UUID;


//@AllArgsConstructor
public class SearchScreen implements IScreen {
   
    private ProductService productService;
    private RouterService routerService;
    private Session session;
    private final CartService cartService;
    
    /**
     * Constructs a new SearchScreen with the specified Session.
     *
     * @param session the Session containing user information
     */
    public SearchScreen( ProductService productService, RouterService routerService, Session session, CartService cartService ) {
        this.productService = productService;
        this.routerService = routerService;
        this.session = session;
        this.cartService = cartService;
    }

    @Override
    public void start(Scanner scan) {
        String input = "";
        clearScreen();

        System.out.println("Welcome To Product Search Screen, " + session.getUsername() + "!");;
        
        System.out.print("\n[x] Exit");
        System.out.print("\nEnter Your Choice ");

        exit: {
            while (true) {
                //clearScreen();
                System.out.println("Welcome To Product Search Screen, " + session.getUsername() + "!");

                System.out.println("\n[1] Search By Product Name");
                System.out.println("\n[2] Search By Product Cateory");
                System.out.println("\n[3] Search By Product Price");
                System.out.println("\n[x] Exit");
                
                System.out.print("\nEnter: ");
                
                input = scan.nextLine();
                
                switch (input.toLowerCase()) {
                    case "1":
                        productSearch(scan, input);
                        break;
                       
                    case "2":
                        categorySearch(scan, input);
                        break;
                    
                    case "3":
                        searchByPriceRange(scan, input); 
                        break;
                    
                    case "x":
                        break exit;
                    
                    default:
                        clearScreen();
                }
            }
        }

    }
    

    /*
     * ------------------------ Helper methods ------------------------------
     */

    /**
     * Clears the console screen.
     */
    private void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public void productSearch(Scanner scan, String input){
        while(true){
            System.out.println("Search by Product Name ");
            System.out.println("\nEnter Product Name:\n  ");
            System.out.println("Enter x to exit:");
            
            input = scan.nextLine().trim();
        
            if(input.isEmpty()){
                System.out.println("Product name empty. Enter valid product name.");
                System.out.println("Enter x to exit:");
                continue;
            }

            if( input.equals("x") ){
                break;
            }
            
            List<Products> products = productService.searchProductByName(input);
            
            if(products.isEmpty()){
                System.out.println("No products found with that name, search again: ");
                continue;
            } else{
                clearScreen();
                System.out.println("\nProduct found:");
                System.out.println("\nEnter x to go back: \n");
                // Print the product details

                displayList(products, "...Search By Prouduct Name\n Products Found: \n");

                System.out.println(" Pick Your Item or Enter x to exit");
                input = scan.nextLine();
                String status = processInput(input, products);
    
                if ( status == "invalidOption" || status == "x" ){
                    break;
                } 
                
                continue; // Get more selections

            }
        }
    }

    public void categorySearch(Scanner scan, String input){

        List<String> categoryList = productService.getAllCategories();
    
        while(true){
            //clearScreen();
            System.out.println("\nSearching by Product Category ");
    
            System.out.println("\nProduct Categories: \n");
            for(int i = 0; i < categoryList.size(); i++){
                System.out.println("[" + (i+1) + "] " + categoryList.get(i));
            }
            System.out.println("\nEnter Product Category:  ");
            System.out.println("x to go back <- ");
            input = scan.nextLine().trim();
    
            if(input.equalsIgnoreCase("x")){
                break;
            }
            else if(input.isEmpty()){
                //clearScreen();
                System.out.println("Invalid product category, please enter a valid product category.");
                System.out.println("Press Enter to try again.");
                scan.nextLine();
                continue;
            }
            
            List<Products> products = productService.getByCategory(input);
            if(products.isEmpty()){
                System.out.println("No products found with that category, search again: ");
                continue;
            }
            else{
                //clearScreen();
                System.out.println("\nProduct found:");
                System.out.println("Enter x to go back: \n");

                displayList(products, "Product List:");
                System.out.println(" Pick Your Item or Enter x to exit");
                input = scan.nextLine();
                String status = processInput(input, products);

                if ( status == "invalidOption" || status == "x" ){
                    break;
                } 
                continue; // Get more selections
            }       
        }
    }

    public void searchByPriceRange(Scanner scan, String input){
        
        while(true){
            //clearScreen();
            System.out.println("\nSearching by Price Range ");

            if(input.equalsIgnoreCase("x")){
                break;
            }

            System.out.println("---Enter Price Range ------");

            // get min value from user
            System.out.print("Please enter minimum amt: ");
            Double min = 0.0;
            input = scan.nextLine();

            if (input.equalsIgnoreCase("x")){
                break;
            }

            if(isNumber(input) && Double.parseDouble(input) >= 0.0){
                min = Double.parseDouble(input);
            }

             //get max value from user
             System.out.print("Please enter maximum amt: ");
             Double max = 0.0;
             input = scan.nextLine();

            if(input.equalsIgnoreCase("x")){
                break;
            }

            if(isNumber(input) && Double.parseDouble(input) >= 0.0){
                 max = Double.parseDouble(input);
            }

            // get list of products from database
            List<Products> products = new ArrayList<Products>();
            products = productService.getByPriceRange(min, max);
            displayList(products, "--------Products in the price range---------------");
           

            System.out.println(" Pick Your Item to Add to cart or Enter x to exit");
            input = scan.nextLine();
            String status = processInput(input, products);

            if ( status == "invalidOption" || status == "x" ){
                break;
            } 
            
            continue; // Get more selections

        }
    }
    
    private static boolean isNumber(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            //System.out.println("u entered " + strNum);
            int i = Integer.parseInt(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }    

    private void displayList(List<Products> products, String label){
        //loop through products and output each product
        //clearScreen();
        System.out.println(label);
        for(int i = 0; i < products.size(); i++){   
            Products product = products.get(i);
            System.out.println( i+1 + ") " +  " Category: " + product.getCategory_name() + " ProductName: " + 
            product.getProduct_name() + " Price: " + product.getPrice() + " Stock: " +  product.getStock() );
            System.out.println("");    
        }    
    }

    private String processInput(String input, List<Products> productList){
        String status = "success";

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
                } else{
                    int quantity = 1;
                
                    Carts cart = addToCart(product);

                    System.out.println(addCartItem(product, quantity, cart.getCart_id()));
    
                    status =  "success";
                } 
            }
        
        } catch(NumberFormatException e){
            switch (input) {
                case "x":
                    //logger.info("User signed out");
                    return "x";
                
                default:
                    //logger.warn("Invalid option!");
                    clearScreen();
                    return "invalidOption";
             }
        }
        return status;

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

}//EndOfClass




