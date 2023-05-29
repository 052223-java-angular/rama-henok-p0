package com.revature.rhshop.screens;

import java.util.List;
import java.util.Scanner;

import com.revature.rhshop.models.CartItems;
import com.revature.rhshop.services.CartService;
import com.revature.rhshop.services.RouterService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CartScreen implements IScreen{

    private final RouterService routerService;
    private final CartService cartService;
    // private final ProductService productService;

    @Override
    public void start(Scanner scan) {
        String input = "";
        String newQuantity = "";

        exit: {
            while(true){
                ClearScreen();

                System.out.println(" Welcome to Your Cart:...");

                List<CartItems> cartItemsList = cartItemsFinder();
                // while this list has next display the code below
                // cartItemsList.stream().forEach(System.out::print);

                for(int i = 0; i < cartItemsList.size(); i++){
                    CartItems cartItems = cartItemsList.get(i);
                    int index = i+1;
                    System.out.println("\nItemNum " + index + "     Product Name: " + cartItems.getProduct_name()+ 
                    "       Price: " + cartItems.getPrice() + "     Quantity= " + cartItems.getQuantity()); //add product name from cartitems table
                }

                System.out.println("---------------------------------------------------------------------------------------------");
                System.out.println("\n[R] Remove    " + "[E] Edit Quantity  " + "[C] Checkout");
                System.out.println("\n[B] Browse    " + "[M] Main Menu   ");

                System.out.print("\nEnter Option (x ot cancel):  ");
                input = scan.nextLine();


                switch (input.toLowerCase()) {
                    case "r":
                    System.out.println("\nEnter Item Number you want to Remove...");
                    int itemIndex = scan.nextInt() -1; 
                        
                    CartItems cartItems = cartItemsList.get(itemIndex);
                    System.out.println("You selected: " + cartItems.getProduct_name());
                    scan.nextLine();
    
                        // remove from cart item by using cart_item_id 
                        String cartItemId = cartItems.getCart_item_id();
                        
                        boolean bool = cartService.delete(cartItemId);
                        if(bool = true){

                            System.out.println("Item deleted From cart Successfuly!...");
                            scan.nextLine();

                            routerService.navigate("/cart", scan);
                        
                            break;
                        }
                        System.out.println("Item Could not be deleted....");
                        scan.nextLine();
                        break exit;

                    case "e":
                        System.out.println("\nEnter Quantity: ");
                        newQuantity = scan.nextLine();

                        //checking if the user endered a number only input
                        if(!newQuantity.matches("^\\d+$")){
                            System.out.println("\nPlease enter Whole Numbers only!");
                            System.out.println(" press Enter to continue....");
                            scan.nextLine();
                            break;
                        }
                        
                        int intValue = Integer.parseInt(newQuantity);
                        String cart_item_id = "69";
                        cartService.updateQuantity(cart_item_id, intValue); 
                        // cartService.findById();    this option needs to get cart_id from cart table using the user id i can get the cart id
                        //fusing cart_id i can get the cart items of the user then i can get all the info in the cart                  

                        //
                        break exit;



                    case "c":
                        //this will checkout the cart and clear cart too and save all cart items into order history 
                        
                        ClearScreen();
                        System.out.println(" Checking out and Completing Order:...");
                        System.out.println(" \nPrice " ); //add total price by calculating quantity * price from cartitems table
                        System.out.println(" \nTax:  "); // total price * 0.08
                        System.out.println(" \nShipping fee:  $ 5.00"); // 
                        System.out.println(" \n\nTotal: "); // add price + tax + shipping fee
                        System.out.println(" \nPress Enter to Confirm (x to cancel and clear cart)"); //
                        String confirm = scan.nextLine();

                        if(confirm.toLowerCase().equals("x")){
                            //code to clear cart items table
                            
                            break;
                        }
                        here:{
                            while(true){
                        System.out.println("\nEnter Card Number:    ");
                        String CardDetails = scan.nextLine();
                        if(!CardDetails.matches("^\\d+$")){
                            System.out.println("\nPlease enter Whole Numbers only!");
                            System.out.println(" press Enter to continue....");
                            scan.nextLine();
                            break here;
                        }

                        System.out.println("\nEnter Card Security code Number:    ");
                        String CardCode = scan.nextLine();
                        if(!CardCode.matches("^\\d+$")){
                            System.out.println("\nPlease enter Whole Numbers only!");
                            System.out.println(" press Enter to continue....");
                            scan.nextLine();
                            continue;
                        }

                        System.out.println("\nEnter Card Expiry day :    ");
                        String CardExpy = scan.nextLine();
                        scan.nextLine();
                        
                        break;
                    }
                        System.out.println("\nPayment Processed Successfully...");
                        System.out.println("Thank you for Placing Your Order...");
                        break exit;
                    }
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

    private List<CartItems> cartItemsFinder(){
        return  cartService.findAll();
    }

    // private Products productFinder(){

    // }


}
