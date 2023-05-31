package com.revature.rhshop.screens;

import java.util.List;
import java.util.Scanner;

import com.revature.rhshop.models.OrderItems;
import com.revature.rhshop.models.Orders;
import com.revature.rhshop.models.Products;
import com.revature.rhshop.services.OrdersService;
import com.revature.rhshop.services.ProductService;
import com.revature.rhshop.utils.Session;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class OrderScreen implements IScreen {
    private Session session;
    private OrdersService ordersService;
    private ProductService productService;

    @Override
    public void start(Scanner scan) {
        while(true){
            ClearScreen();

            System.out.println(" Welcome to Your Order History: " + session.getUsername() + "...");
            List<Orders> purchasedItemsList = orderItemsFinder(session.getId());
            Orders purchased = ordersService.purchasedOrders(session.getId());

            List<OrderItems> purchasedItems = purchasedItemsFinder(purchased.getOrder_id());

            Products products = productService.findById(purchased.getOrder_id());
            System.out.println(products);

            
            
            // emptyCart(purchasedItemsList);
            for(int i = 0; i < purchasedItemsList.size(); i++){
                Orders cartItemsDisplay = purchasedItemsList.get(i);
                int index = i+1;
                System.out.println("\nItemNum " + index + "     Product Name: " + products.getProduct_name() + 
                "       Order Time:  " + purchased.getOrder_time() + "     Quantity= "); //add product name from cartitems table
            }

            System.out.println("---------------------------------------------------------------------------------------------");
            System.out.println("\n[R] Remove    " + "[E] Edit Quantity  " + "[C] Checkout");
            System.out.println("\n[B] Browse    " + "[M] Main Menu   ");

        }
    }

  private void ClearScreen() {  
    System.out.print("\033[H\033[2J");  
    System.out.flush();  
    }

    //-------------------helper Methods ------------------/


    private List<Orders> orderItemsFinder(String user_id){
        return  ordersService.findAll(user_id);
    }
    private List<OrderItems> purchasedItemsFinder(int order_id){
        return  ordersService.findAllpurchasedItemsFinderByUser(order_id);
    }

    private boolean emptyCart(Orders purchasedItemsList){
        if(purchasedItemsList.equals(null)){
            System.out.println("\n\nYour History is Empty...");
            System.out.println("You can check out our new items by browsing our shop!.... ");
            System.out.println("\nRedirecting you to Product browsing Page .. .. . ..  .");
            return false;
        }
        return true;
    }

}