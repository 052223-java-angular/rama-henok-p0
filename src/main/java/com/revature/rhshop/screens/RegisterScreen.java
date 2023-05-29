package com.revature.rhshop.screens;

import java.util.Scanner;

import com.revature.rhshop.models.User;
import com.revature.rhshop.services.UserService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class RegisterScreen implements IScreen {

    private final UserService userService;

    @Override
    public void start(Scanner scan) {
        String input = "";
        String username = "";
        String password = "";


        exit:{
            while(true){
                ClearScreen();
                System.out.println("\nWelcome to RH Shop Please Register");

                //get username
                username = getUsername(scan);

                if(username.equals("x")){
                    break exit;
                }


                //get password
                password = getPassword(scan);
                if(password.equals("x")){
                    break exit;
                }

                //confirming credentials entered
                ClearScreen();
                System.out.println("Please confirm your credentials: ");
                System.out.println("\nUsername: " + username);
                System.out.println("\nPassword: " + password);
                System.out.println("Confirm Credentials (y/n): ");
                String confirmation = scan.nextLine();
                switch(confirmation.toLowerCase()){
                    case "y":
                        // User createdUser = userService.Register(username, password);
                        userService.Register(username, password);
                        break exit;
                    case "n":
                        ClearScreen();
                        System.out.print("\nRestarting Registration ...");
                        System.out.print("\nPress enter to continue...  ");
                        scan.nextLine();
                        break;
                    default:
                        ClearScreen();
                        System.out.print("\nInvalid option pressed!");
                        System.out.print("\nPress enter to continue...  ");
                        scan.nextLine();
                        break;
                }

                //breakout if everything is correct else go to xit and repeat till correct
                break exit;
            }
        }
        
    }
    
            /*------------------> Healper Methods <----------------  */

    public String getUsername(Scanner scan){
        String username = "";

        while(true){
        System.out.print("\nEnter a User Name (x to cancel):  ");

        username = scan.nextLine();

        if(username.equalsIgnoreCase("x") ){
            return "x";
        }

        if(!userService.usernameValidator(username)){
            ClearScreen();
            System.out.println("\n Invalid Username please Notice the following and Retry: ");
            System.out.println("\n          -> Username needs to be 8-20 characters long");
            System.out.println("\n          -> Username Should not Contain Space");
            System.out.println("\n          -> Username should contain only . and _");
            System.out.println("\nPress enter to continue: ");
            scan.nextLine();
            continue;    
        }

        if(!userService.isuniqueUsername(username)){
            ClearScreen();
            System.out.println("\nUsername needs to be unique");
            System.out.println("\nPress enter to continue: ");
            scan.nextLine();
            continue;
        }

        break;

        }

        return username;
    }

    public String getPassword(Scanner scan){
        String username = "";
        String confirmPassword = "";

        while(true){
        System.out.print("\nEnter a New Password (x to cancel):  ");

        username = scan.nextLine();

        if(username.equalsIgnoreCase("x") ){
            return "x";
        }

        if(!userService.passwordValidator(username) ){
            ClearScreen();
            System.out.println("\n Invalid Password please Notice the following and Retry: ");
            System.out.println("\n          -> Minimum eight characters");
            System.out.println("\n          -> at least one letter and one number:");
            System.out.println("\nPress enter to continue: ");
            scan.nextLine();
            continue;    
        }

            System.out.println("\nConfirm Password (x to cancel): ");
            confirmPassword = scan.nextLine();
            if(confirmPassword.equalsIgnoreCase("x")){
                return "x";
            }

        if(!userService.passwordConfirmation(username, confirmPassword)){
            ClearScreen();
            
            System.out.println("\nPasswords do not match: ");
            scan.nextLine();
            continue;
        }

        break;

        }

        return username;
    }
    
    private void ClearScreen() {  
        System.out.print("\033[H\033[2J");  
        System.out.flush();  
    }
}
