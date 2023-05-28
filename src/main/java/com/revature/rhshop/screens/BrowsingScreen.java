package com.revature.rhshop.screens;

import com.revature.rhshop.models.Products;
import com.revature.rhshop.utils.Session;

import java.util.List;
import java.util.Scanner;
import com.revature.rhshop.services.RouterService;
import com.revature.rhshop.services.ProductService;
import org.apache.logging.log4j.LogManager;

//import com.revature.rhshop.services.ProductService;
import org.apache.logging.log4j.Logger;
import lombok.AllArgsConstructor;

//@AllArgsConstructor
public class BrowsingScreen implements IScreen{
    private final ProductService productService;
    private Session session;
    RouterService router;
    
    
    public BrowsingScreen(ProductService productService, RouterService routerService, Session session) {
        this.productService = productService;
        this.router = routerService;
        this.session = session;
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
                    int index = i+1;
                    System.out.println("ItemNum: " + index + " ProductName: "  + product.getProduct_name() + 
                    " Price: $" + product.getPrice() + " In Stock: " + product.getStock() );
                    System.out.println("");
                }
                
                System.out.println("\n[x] Exit");
                //System.out.print("\nEnter Item Number: ");
                input = scan.nextLine();

                try {
                    Integer.parseInt(input);

                } catch (NumberFormatException e){
                   if ( input.equals("x")){
                        break exit;
                   }
                }

                /*** 
                
                try{ 
                    int productIndex = Integer.parseInt(input) -1; 
                    if(productIndex >= 0 && productIndex < productList.size()){
                        Products product = productList.get(productIndex);
                        System.out.println("Product Details: " + product.getProduct_name());
                        System.out.println("Enter x");
                        scan.nextLine();
                        //router.navigate("/browse", scan);
                    } else{
                        logger.warn("Invalid option!");
                        clearScreen();
                    }
                }   catch(NumberFormatException e){
                        switch (input) {
                            case "x":
                                logger.info("User signed out");
                            break exit;
                        default:
                        logger.warn("Invalid option!");
                        clearScreen();
                    }
                } **/
            }//endwhile
                
        } //endexit
    }


    private void clearScreen() {
    System.out.print("\033[H\033[2J");
    System.out.flush();
    }
 
}