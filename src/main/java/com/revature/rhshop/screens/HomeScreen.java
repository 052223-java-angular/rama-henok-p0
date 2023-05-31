package com.revature.rhshop.screens;

import java.util.Scanner;

import com.revature.rhshop.services.RouterService;

import lombok.AllArgsConstructor;

//this annotation will create all argument constructor for the private RouterService
@AllArgsConstructor 
public class HomeScreen implements IScreen{
    private final RouterService router;

    @Override
    public void start(Scanner scan) {

        String input = "";
        
        exit: {
            while(true){
                ClearScreen();

                System.out.print("\n\nWelcome to RH Shop!");
                System.out.print("\n\n");
                System.out.print("\n[1] Login ");
                System.out.print("\n[2] Register    ");
                System.out.print("\n[x] Exit");

                
                System.out.print("\nEnter:  ");
                input = scan.nextLine();

                switch (input.toLowerCase()) {
                    case "1":
                    router.navigate("/login", scan);
                        break;
                    case "2":
                        router.navigate("/register", scan);
                        break;
                    case "x":
                        System.out.print("\n.....Exit Successful Goodbye!.......");
                        break exit;
                    default:
                        ClearScreen();
                        System.out.print("\nInvalid option pressed!");
                        System.out.print("\nPress enter to continue...  ");
                        scan.nextLine();
                        break;
                }
            }
        }
      }

        /*------------------> Healper Methods <----------------  */

    private void ClearScreen() {  
        System.out.print("\033[H\033[2J");  
        System.out.flush();  
    } 

}
