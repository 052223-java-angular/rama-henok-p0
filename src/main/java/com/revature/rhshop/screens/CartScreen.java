package com.revature.rhshop.screens;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.UUID;

import com.revature.rhshop.models.CartItems;
import com.revature.rhshop.models.OrderItems;
import com.revature.rhshop.models.Products;
import com.revature.rhshop.services.CartService;
import com.revature.rhshop.services.ProductService;
import com.revature.rhshop.services.RouterService;
import com.revature.rhshop.utils.Session;
import com.revature.rhshop.utils.customeExceptions.InvalidIndexInsertedException;

import lombok.AllArgsConstructor;


@AllArgsConstructor
public class CartScreen implements IScreen{
    private final RouterService routerService;
    private final CartService cartService;
    private Session session;
    private final ProductService productService;

    @Override
    public void start(Scanner scan) {
        String input = "";
        String intValue = "";

        exit: {
            while(true){
                ClearScreen();

                System.out.println(" Welcome to Your Cart: " + session.getUsername() + "...");

                List<CartItems> cartItemsList = cartItemsFinder();
                // while this list has next display the code below
                // cartItemsList.stream().forEach(System.out::print);
                if(cartItemsList.isEmpty()){
                    System.out.println("Your Cart is Empty...");
                    System.out.println("You can check out out new items by browsing our shop!.... ");
                }

                for(int i = 0; i < cartItemsList.size(); i++){
                    CartItems cartItems = cartItemsList.get(i);
                    int index = i+1;
                    System.out.println("\nItemNum " + index + "     Product Name: " + cartItems.getProduct_name()+ 
                    "       Price: $ " + cartItems.getPrice() + "     Quantity= " + cartItems.getQuantity()); //add product name from cartitems table
                }

                System.out.println("---------------------------------------------------------------------------------------------");
                System.out.println("\n[R] Remove    " + "[E] Edit Quantity  " + "[C] Checkout");
                System.out.println("\n[B] Browse    " + "[M] Main Menu   ");

                System.out.print("\nEnter Option (x ot cancel):  ");
                input = scan.nextLine();


                int itemIndex = 0;
                String cartItemId = "";
                CartItems cartItems = new CartItems();

                switch (input.toLowerCase()) {
    
                    case "r":
                        System.out.println("\nEnter Item Number you want to Remove...");
                        itemIndex = scan.nextInt() -1; 
                        if(!(itemIndex < cartItemsList.size())){
                            
                        System.out.println("Item Could not be removed....");
                        scan.nextLine();
                        break;
                        }    

                            cartItems = cartItemsList.get(itemIndex);
                            System.out.println("You selected: " + cartItems.getProduct_name());
                            scan.nextLine();
        
                            // remove from cart item by using cart_item_id 
                            cartItemId = cartItems.getCart_item_id();
                            
                            
                            if(cartService.delete(cartItemId) == true){

                                System.out.println("Item removed From cart Successfuly!...");
                                scan.nextLine();

                                routerService.navigate("/cart", scan);

                            }
                            
                            scan.nextLine();
                            break;
                        // }
                        // break exit;
//,,,,,,,,,,,,,,
                    case "e":
                        System.out.println("\nEnter Item Number you want to Edit quantity...");
                        itemIndex = scan.nextInt() -1; 

                        if(!(itemIndex < cartItemsList.size())){
                            
                            System.out.println("Invalid Item number pressed....");
                            scan.nextLine();
                            break;
                            }    
                            
                        cartItems = cartItemsList.get(itemIndex);
                        System.out.println("You selected: " + cartItems.getProduct_name());
                        scan.nextLine();
    
                        // edit quantity of the item from cart item by using cart_item_id 
                        cartItemId = cartItems.getCart_item_id();
                        
                        System.out.println("\nEnter Quantity: ");
                        intValue = scan.nextLine();

                        //checking if the user endered a number only input
                        if(!intValue.matches("^\\d+$")){
                            System.out.println("\nPlease enter Whole Numbers only!");
                            System.out.println(" press Enter to continue....");
                            scan.nextLine();
                            break;
                        }
                        
                        int product_id = cartItems.getProduct_id();
                        int stock = this.productFinder(product_id).getStock();

                        int newQuantity = Integer.parseInt(intValue);
                        if(stock < newQuantity){
                            System.out.println("You entered an amount which is greater than our stock...");
                            System.out.println("we have: " + stock + " amounts of " + cartItems.getProduct_name() + " in our Stock.");
                            System.out.println("please enter new amount");
                            scan.nextLine();
                            break;
                        }
                        cartService.updateQuantity(cartItemId, newQuantity); 
                        // cartService.findById();    this option needs to get cart_id from cart table using the user id i can get the cart id
                        //fusing cart_id i can get the cart items of the user then i can get all the info in the cart                  

                        //
                        break;

                    case "c":
                        //this will checkout the cart and clear cart too and save all cart items into order history 
                        double totalPrice = priceCalculator();

                        ClearScreen();
                        System.out.println(" Checking out and Completing Order of " + session.getUsername() + " :...");
                        System.out.println(" \nTotal Price $" + formater(totalPrice)); 
                        double tax = totalPrice * 0.08;
                        
                        System.out.println(" \nTax:  " + formater(tax)); // total price * 0.08
                        System.out.println(" \nShipping fee:  $ 5.00"); // 
                        double finalAmount = totalPrice + tax + 5;

                        System.out.println(" \n\ncart Total= $ " + formater(finalAmount)); // add price + tax + shipping fee
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
                        if(movingCartItems(cartItems)){
                            cartService.celarCart();
                        }

                        scan.nextLine();
                        System.out.println("Thank you for Placing Your Order...");
                        scan.nextLine();
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

    private List<CartItems> cartItemsFinder(){
        return  cartService.findAll();
    }

    private Products productFinder(int product_id){

        return productService.findById(product_id);

    }

    private double priceCalculator(){
      return  cartService.totalPriceCalculator();
    }
    
    private String formater(double number){
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        String numbeString = decimalFormat.format(number);

        return numbeString;

    }

     private void ClearScreen() {  
        System.out.print("\033[H\033[2J");  
        System.out.flush();  
    } 

    private boolean movingCartItems(CartItems cartItems){

        OrderItems orderItems = new OrderItems();

        Random random = new Random();

        orderItems.setOrder_id(random.nextInt());

         int order_item_id;
         int quantity;
         float price;
         int order_id;
         int product_id;



        return true;
    }


}
