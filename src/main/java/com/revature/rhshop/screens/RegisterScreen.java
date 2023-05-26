package com.revature.rhshop.screens;

import java.util.Scanner;

public class RegisterScreen implements IScreen {

    @Override
    public void start(Scanner scan) {
        String input = "";

        exit:{
            while(true){
                ClearScreen();
                System.out.print("\nWelcome to the register screen");
                scan.nextLine();
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
