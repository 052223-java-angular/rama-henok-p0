package com.revature.rhshop.screens;

import java.util.Scanner;
import com.revature.rhshop.services.RouterService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.revature.rhshop.utils.Session;
import lombok.AllArgsConstructor;

/**
 * The MenuScreen class represents the menu screen of the Yolp Application.
 * It implements the IScreen interface.
 */
@AllArgsConstructor
public class MenuScreen implements IScreen {
   
    private Session session;
    private RouterService routerService;

    private static final Logger logger = LogManager.getLogger(RegisterScreen.class);

     /**
     * Constructs a new MenuScreen with the specified Session.
     *
     * @param session the Session containing user information
     */
    public MenuScreen(RouterService routerService, Session session) {
        this.session = session;
        this.routerService = routerService;
    }

    @Override
    public void start(Scanner scan) {
        String input = "";
        clearScreen();

        System.out.println("Welcome To menu screen, " + session.getUsername() + "!");

        System.out.println("Press x to logout");
        System.out.println("[1] Browse All Products");
        System.out.println("[2] Search for Products");
        
        System.out.print("\n[x] Exit");
        System.out.print("\nEnter Your Choice ");

        exit: {
            while (true) {
                clearScreen();
                System.out.println("Welcome to the Menu screen, " + session.getUsername() + " !");
                System.out.println("\n[1] Shopping Cart");
                System.out.println("\n[2] Browse Products");
                System.out.println("\n[3] Search Products");
                System.out.println("\n[4] View Order History");
                System.out.println("\n[5] Review Products");
                System.out.println("\n[x] Exit");
                System.out.print("\nEnter: ");
                input = scan.nextLine();
                
                switch (input.toLowerCase()) {
                    case "1":
                        logger.info("Navigating to Shopping Cart");
                        routerService.navigate(("/cart"), scan);
                    break;
                    
                    case "2":
                        logger.info("Navigating to Product Screen");
                        // System.out.println("2");
                        routerService.navigate("/browse", scan);
                    break;

                    case "3":
                        logger.info("Navigating to Search Screen");
                        // System.out.println("2");
                        routerService.navigate("/search", scan);
                    break;

                    case "4":
                        logger.info("Navigating to Order Screen");
                        System.out.println("This feature Will be Available soon......");
                        scan.nextLine();
                        // routerService.navigate("/order", scan);

                    break;

                    case "5":
                        logger.info("Navigating to Review Screen");
                        // System.out.println("2");
                        routerService.navigate("/review", scan);
                    break;
                    
                    
                    case "x":
                        logger.info("user sign out");
                        session.clearSession();
                        break exit;
                    
                    default:
                        logger.warn("Invalid option!");
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
}