package com.revature.rhshop.screens;


import java.util.logging.LogManager;
import java.util.logging.Logger;

import lombok.AllArgsConstructor;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import com.revature.rhshop.models.Products;
import com.revature.rhshop.utils.Session;
import com.revature.rhshop.services.ProductService;
import com.revature.rhshop.services.RouterService;


//@AllArgsConstructor
public class SearchScreen implements IScreen {
   
    private ProductService productService;
    private RouterService routerService;
    private Session session;
    
    /**
     * Constructs a new SearchScreen with the specified Session.
     *
     * @param session the Session containing user information
     */
    public SearchScreen( ProductService productService, RouterService routerService, Session session ) {
        this.productService = productService;
        this.routerService = routerService;
        this.session = session;
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
                clearScreen();
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
                        //categorySearch(scan, input);
                        break;
                    
                    case "3":
                        //searchByPriceRange(scan, input); 
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
                for(int i = 0; i < products.size(); i++){
                    Products product = products.get(i);
                    System.out.println(i +1 + " ProductName " + 
                    product.getProduct_name() + " " +
                    " Category " +  product.getCategory_name() + " Price " + product.getPrice() + " Stock " + product.getStock() );
    
                    input = scan.nextLine();
                    
                    if(input.equals("x")){
                        break;
                    }
              
                    input = scan.nextLine();
                }


            }
        }
       
    }

    




}




