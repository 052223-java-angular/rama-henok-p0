package com.revature.rhshop.screens;


import java.util.Scanner;
import com.revature.rhshop.utils.Session;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.revature.rhshop.models.User;
import com.revature.rhshop.services.RouterService;
import com.revature.rhshop.services.UserService;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.mindrot.jbcrypt.BCrypt;

@AllArgsConstructor
public class LoginScreen implements IScreen{
    private final RouterService routerService;
    private final UserService userService;
    private static final Logger logger = LogManager.getLogger(RegisterScreen.class);
    private Session session;
    
        @Override
        public void start(Scanner scan) {
            String input = "";
            String username = "";
            String password = "";
            boolean passwordMatch = true;
            Optional<User> user;

            exit : {
                while(true) {
                    logger.info("Login RHshop!");
                    clearScreen();
                    System.out.println("Login here! (x to cancel)");
                    username = getUsername(scan);
                    logger.info("username " + username);
                    //System.out.println("You Entered " + username);

                    if (username.equals("x")) {
                        //logger.info("Leaving login screen");
                        break exit;
                    }
                    // System.out.println("get password");

                    System.out.print("\nEnter your password (x to cancel):");
                    System.out.flush();
                  
                    password = getPassword(scan);
                    //logger.info("password " + password);
                    System.out.flush();
                        
                    if (password.equals("x")) {
                        logger.info("Leaving login screen");
                        break exit;
                    }

                    user = userService.login(username, password);
                    if (user.isEmpty()) {
                        logger.warn("Unsuccessful login!");
                        System.out.println("Username or Password are incorrect, please try again");
                        System.out.print("\nPress enter to continue...");
                        scan.nextLine();
                        continue;
                    } 
                    
                    session.setSession(user.get());
                    routerService.navigate("/menu", scan);
                    break exit;
                }
                    
            }
        }
        public String getUsername(Scanner scan) {
            String username = "";

            while (true) {
                System.out.print("\nEnter a username:");
              
                username = scan.nextLine();
                //System.out.println("username1 " + username );
                if (username.equalsIgnoreCase("x")) {
                    return "x";
                }
                break;
            }
            //System.out.println("username2 " + username );
            return username;
        }


        public String getPassword(Scanner scan) {
            String password = "";

            while(true){
                System.out.println("\nEnter Password (x to cancel): ");
                password = scan.nextLine();
                
                //scan.nextLine();
            
                if (password.equalsIgnoreCase("x")){
            
                    return "x";
                }
                 break;
            }
            return password;
         
        }
        
        
        private void clearScreen() {
            System.out.print("\033[H\033[2J");
            System.out.flush();
        }

}