package com.revature.rhshop.screens;

import com.revature.rhshop.models.Users;
import com.revature.rhshop.services.UserService;
import java.util.Scanner;
import com.revature.rhshop.models.Users;

import lombok.AllArgsConstructor;
import java.util.Optional;


@AllArgsConstructor
public class LoginScreen implements IScreen {

    private final UserService userService;

    @Override
    public void start(Scanner scan) {
        String username = "";
        String password = "";
        

        exit:{
            while(true){
                ClearScreen();
                System.out.print("\nWelcome to the Login screen");

                // Get username
                System.out.println("\nEnter Username (x to cancel): ");
                username = scan.nextLine();

                if (username.equals("x")) {
                    break exit;
                }

                //get password
                System.out.println("\nEnter Password (x to cancel): ");
                password = scan.nextLine();
                 
                if(password.equals("x")) {
                     break exit;
                }

                Optional<Users> user = userService.login(username, password);
                
                if (user.isEmpty()) {
                    System.out.println("\nInvalid login credentials. Please try again.");
                    System.out.println("\nPress enter to continue...");
                    scan.nextLine();
                    continue;
                } 

                System.out.println("username " + username + " password " + password );
               
                
                
                break exit;
            }
        }
        
    }
    
    /*------------------> Healper Methods <----------------  */

    private void ClearScreen() {  
        System.out.print("\033[H\033[2J");  
        System.out.flush();  
    }
}

   