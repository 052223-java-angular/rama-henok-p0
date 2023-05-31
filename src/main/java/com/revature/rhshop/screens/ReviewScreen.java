package com.revature.rhshop.screens;

import com.revature.rhshop.models.Products;
import com.revature.rhshop.models.Reviews;
import java.util.Scanner;
import com.revature.rhshop.services.RouterService;
import com.revature.rhshop.services.ProductService;
import com.revature.rhshop.services.ReviewService;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.revature.rhshop.utils.Session;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Scanner;


/**
 * The MenuScreen class represents the menu screen of the Yolp Application.
 * It implements the IScreen interface.
 */
//@AllArgsConstructor
public class ReviewScreen implements IScreen {
    private final ProductService productService;
    private Session session;
    private RouterService routerService;
    private ReviewService reviewService;


     /**
     * Constructs a new MenuScreen with the specified Session.
     *
     * @param session the Session containing user information
     */
    public ReviewScreen(ProductService productService, RouterService routerService, Session session, ReviewService reviewService) {
        this.productService = productService;
        this.session = session;
        this.routerService = routerService;
        this.reviewService = reviewService;
    }

    @Override
    public void start(Scanner scan) {
        String input = "";
        clearScreen();

        System.out.println("Welcome To Review screen, " + session.getUsername() + "!");
        //System.out.print("\n[x] Exit");

        exit: {
            while (true) {
                System.out.println("Enter [R] - View Review List ");
                System.out.println("Enter [A] - Add Review List ");
                System.out.println("Enter x to Exit");
                input = scan.nextLine().trim();
                 
        
                if(input.isEmpty()){
                    System.out.println("Invalid Option.");
                    continue;
                } 
        
                if( input.equalsIgnoreCase("x") ){
                    break exit;
                }

                List<Products> productList = productService.getAll();
             

                if ( input.equalsIgnoreCase("r") ){
                    input = showReviews(productList, scan);
                    if (input.equalsIgnoreCase("x")){
                        break exit;
                    }
                    break;
                }

                if ( input.equalsIgnoreCase("a") ){
                    System.out.println("Implementation in Progress");
                    if (input.equalsIgnoreCase("x")){
                        break exit;
                    }
                    break;
                }
                
            }
                
        } //endOfexit
    }//endOfStart

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


    private String showReviews(List<Products> products, Scanner scan){
        // Get all info from review table for that product
        if ( products.size() == 0 ){
            System.out.println("No products To Review");
            return "continue";
        }

        displayList(products, "Product Review");
        // Get user selection
        System.out.println("Pick Product Item");
        String input = scan.nextLine().trim();
        String status = processInput(input, products );
        if ( status.equalsIgnoreCase("x") ){
            return "x";
        }
        return "continue";

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
                // Get Review Details
                List<Reviews> reviewList = reviewService.getReviewsByProductName(product.getProduct_name());
                // Print the details
                if( reviewList.size() == 0 ){
                    System.out.println("No Reviews Found");
                }

                for ( int i=0; i<reviewList.size(); i++ ){
                    Reviews review = reviewList.get(i);
                    System.out.println( i+1 + ") " + " ProductId" + review.getProduct_id() + " Rating: " + review.getRating() + " \nComment: " + review.getComment() );
                   
                    System.out.println("");    
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





}
