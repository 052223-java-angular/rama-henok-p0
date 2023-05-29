package com.revature.rhshop.screens;

import java.util.Scanner;

import com.revature.rhshop.services.CartService;
import com.revature.rhshop.services.RouterService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CartScreen implements IScreen{

    private final RouterService routerService;
    private final CartService cartService;

    @Override
    public void start(Scanner scan) {
        String input = "";
        String newQuantity = "";

        exit: {
            while(true){
                ClearScreen();

                System.out.println(" Your Cart:...");
                System.out.println(" \nProduct Name: " ); //add product name from cartitems table
                System.out.println(" \nPrice: "); //Price of product  //edit alignment 
                System.out.println("\n[R] Remove    " + "[E] Edit Quantity  " + "[C] Checkout");
                System.out.println("\n[B] Browse    " + "[M] Main Menu   ");

                System.out.print("\nEnter (x ot cancel):  ");
                input = scan.nextLine();

                switch (input.toLowerCase()) {
                    case "r":
                        //remove from cart database
                        break;
                    case "e":
                        System.out.println("\nEnter Quantity: ");
                        newQuantity = scan.nextLine();
                        if(!newQuantity.matches("^\\d+$")){
                            System.out.println("\nPlease enter Whole Numbers only!");
                            System.out.println(" press Enter to continue....");
                            scan.nextLine();
                            break;
                        }
                        int intValue = Integer.parseInt(newQuantity);
                        cartService.updateQuantity("", intValue);
                        

                        //
                        break exit;
                    case "x":
                        System.out.print("\nExit Successful.....Goodbye!");
                        break exit;
                    case "b":
                        routerService.navigate("/browse", scan);
                        break exit;
                    case "m":
                        routerService.navigate("/menu", scan);
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
    



    /*----------------------> Healper Methods <---------------- */
    private void ClearScreen() {  
        System.out.print("\033[H\033[2J");  
        System.out.flush();  
    } 


}
