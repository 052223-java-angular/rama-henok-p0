package com.revature.rhshop.screens;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import com.revature.rhshop.models.CartItems;
import com.revature.rhshop.models.OrderItems;
import com.revature.rhshop.models.Orders;
import com.revature.rhshop.models.Products;
import com.revature.rhshop.services.CartService;
import com.revature.rhshop.services.OrderItemsService;
import com.revature.rhshop.services.OrdersService;
import com.revature.rhshop.services.ProductService;
import com.revature.rhshop.services.RouterService;
import com.revature.rhshop.utils.Session;
import com.revature.rhshop.utils.customeExceptions.PaymentDeclinedException;

import lombok.AllArgsConstructor;


@AllArgsConstructor
public class CartScreen implements IScreen{
    private final RouterService routerService;
    private final CartService cartService;
    private Session session;
    private final ProductService productService;
    private final OrdersService ordersService;
    private final OrderItemsService orderItemsService;

    @Override
    public void start(Scanner scan) {
        String input = "";
        String intValue = "";
        int itemIndex = 0;
        String cartItemId = "";
        CartItems cartItems = new CartItems();

        exit: {
            while(true){
                ClearScreen();

                System.out.println(" Welcome to Your Cart: " + session.getUsername() + "...");

                List<CartItems> cartItemsList = cartItemsFinder(session.getId());

                emptyCart(cartItemsList);// this will check if the cart is empty or not 

                for(int i = 0; i < cartItemsList.size(); i++){
                    CartItems cartItemsDisplay = cartItemsList.get(i);
                    int index = i+1;
                    System.out.println("\nItemNum " + index + "     Product Name: " + cartItemsDisplay.getProduct_name()+ 
                    "       Price: $ " + cartItemsDisplay.getPrice() + "     Quantity= " + cartItemsDisplay.getQuantity()); //add product name from cartitems table
                }

                System.out.println("---------------------------------------------------------------------------------------------");
                System.out.println("\n[R] Remove    " + "[E] Edit Quantity  " + "[C] Checkout");
                System.out.println("\n[B] Browse    " + "[M] Main Menu   ");

                System.out.print("\nEnter Option (x ot cancel):  ");
                input = scan.nextLine();

                switch (input.toLowerCase()) {
    
                    case "r":

                        if(emptyCart(cartItemsList)){ 

                            System.out.println("\nEnter Item Number you want to Remove...");
                            String inputValue = scan.nextLine();

                         
                            itemIndex = inputValidator(inputValue, cartItemsList.size());
                            
                            if(itemIndex == 0){
                                scan.nextLine();
                                break;
                            }  

                            itemIndex --; //this will decrease the itemIndex by 1 because cartItemsList is a list and its index number starts from 0

                            cartItems = cartItemsList.get(itemIndex);
                            System.out.println("You selected: " + cartItems.getProduct_name());
                            scan.nextLine();
        
                            // remove from cart item by using cart_item_id 
                            cartItemId = cartItems.getCart_item_id();
                            
                            
                            if(cartService.delete(cartItemId, session.getId()) == true){

                                System.out.println("Item removed From cart Successfuly!...");
                                scan.nextLine();

                                routerService.navigate("/cart", scan);
                            }
                            
                                scan.nextLine();
                                break;
                        }else{
                            scan.nextLine();
                            routerService.navigate("/browse", scan);
                            break exit;
                        }
                       
//,,,,,,,,,,,,,,
                    case "e":
                        if(emptyCart(cartItemsList)){ 
                            System.out.println("\nEnter Item Number you want to Edit quantity...");
                            String inputValue = scan.nextLine();

                         
                            itemIndex = inputValidator(inputValue, cartItemsList.size());
                            
                            if(itemIndex == 0){
                                scan.nextLine();
                                break;
                            }  

                            itemIndex --;
                                
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
                            cartService.updateQuantity(cartItemId, newQuantity, session.getId()); 
                            // cartService.findById();    this option needs to get cart_id from cart table using the user id i can get the cart id
                            //fusing cart_id i can get the cart items of the user then i can get all the info in the cart                  

                            //
                        }else{
                            scan.nextLine();
                            routerService.navigate("/browse", scan);
                            break exit;
                        }
                        break;

                    case "c":
                        if(emptyCart(cartItemsList)){ 
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
                                    // break here;
                                    continue;
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
                            System.out.println("\nPayment is Being processed...");

                            CartItems purchasedItems = allCartItemsFinder(session.getId());
                            orderItemsService.movingCartItems(purchasedItems);

                            try {
                                //this will move cart items into orders table to keeo it as an order history

                                if(movingCartItems(cartItems, finalAmount )){
                                    if(cartService.celarCart(session.getId()) > 0){
                                        System.out.println("Payment Processed Successfully");
                                    }
                                }
                            } catch (PaymentDeclinedException e) {
                                e.printStackTrace();
                            }

                            scan.nextLine();
                            System.out.println("Thank you for Placing Your Order...");
                            scan.nextLine();
                            break exit;
                            }
                            }else{
                            scan.nextLine();
                            routerService.navigate("/browse", scan);
                            break exit;
                        }
                        // break;

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

    private CartItems allCartItemsFinder(String user_id) {
        return  cartService.findAllItems(user_id);
    }




    private List<CartItems> cartItemsFinder(String user_id){
        return  cartService.findAll(user_id);
    }

    private Products productFinder(int product_id){

        return productService.findById(product_id);

    }

    private double priceCalculator(){
      return  cartService.totalPriceCalculator(session.getId());
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

    private boolean movingCartItems(CartItems cartItems, double totalPrice) throws PaymentDeclinedException{

        Orders orders = new Orders();

        Random random = new Random();

        orders.setOrder_id(random.nextInt());
        orders.setProduct_name(cartItems.getProduct_name());
        orders.setTotal_cost(totalPrice);
        orders.setOrder_time(LocalDate.now());
        orders.setUser_id(session.getId());

        
        return ordersService.movingCartItems(orders);
    
    }

    private boolean emptyCart(List cartItemsList){
        if(cartItemsList.isEmpty()){
            System.out.println("\n\nYour Cart is Empty...");
            System.out.println("You can check out our new items by browsing our shop!.... ");
            System.out.println("\nRedirecting you to Product browsing Page .. .. . ..  .");
            return false;
        }
        return true;
    }

    private int inputValidator(String input, int size){
        int itemNum = 0;
        if((input.matches("^\\d+$"))){

            itemNum = Integer.parseInt(input);
                if(itemNum > size ){
                    System.out.println("Item Could not be removed (WRONG item number)....");
                    return 0;
                }
                return itemNum;
            }else{
                    System.out.println("Item Could not be removed (WRONG item number)....");
                    return 0;
            } 
    }



}