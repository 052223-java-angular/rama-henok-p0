package com.revature.rhshop.screens;

import java.util.Scanner;

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


                //breakout if everything is correct else go to xit and repeat till correct
                break exit;
            }
        }
        
    }
    
            /*------------------> Healper Methods <----------------  */

    public String getUsername(Scanner scan){

        while(true){
        System.out.print("\nEnter a User Name (x to cancel):  ");

        String username = scan.nextLine();

        if(username.equalsIgnoreCase("x") ){
            return "x";
        }

        if(!userService.usernameValidator(username)){
            ClearScreen();
            System.out.println("\nUsername needs to be 8-20 characters long");
            System.out.println("\nPress enter to continue: ");
            scan.nextLine();
            continue;    
        }

        break;

        }

        return "";
    }
    
    private void ClearScreen() {  
        System.out.print("\033[H\033[2J");  
        System.out.flush();  
    }
}
