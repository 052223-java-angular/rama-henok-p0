package com.revature.rhshop.services;

import java.util.Scanner;

import com.revature.rhshop.daos.CartItemsDAO;
import com.revature.rhshop.daos.CartDAO;
import com.revature.rhshop.daos.RoleDAO;
import com.revature.rhshop.daos.UserDAO;
import com.revature.rhshop.screens.CartScreen;
import com.revature.rhshop.daos.ProductDAO;

import com.revature.rhshop.screens.HomeScreen;
import com.revature.rhshop.screens.RegisterScreen;
import com.revature.rhshop.screens.LoginScreen;
import com.revature.rhshop.screens.MenuScreen;
import com.revature.rhshop.screens.OrderScreen;
import com.revature.rhshop.screens.SearchScreen;
import com.revature.rhshop.screens.BrowsingScreen;
import com.revature.rhshop.utils.Session;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class RouterService {
    private Session session;

    public void navigate(String path, Scanner scan){
        
        switch(path) {

            case "/home":
                //this refers to the class itself(RouterService) because we injected RouterService dependency inside homescreen
                new HomeScreen(this).start(scan);  
            break;

            case "/login":
                 //new LoginScreen(this).start(scan);
                new LoginScreen(this, getUserService(), session).start(scan);
            break;

            case "/register":
                new RegisterScreen(getUserService()).start(scan);
                break;

            case "/menu":
                new MenuScreen(this, session).start(scan);
            break;

            case "/browse":
                new BrowsingScreen(getProductService(), this, session, getCartService() ).start(scan);
            break;


            case "/search":
                new SearchScreen(getProductService(), this, session ).start(scan);
            break;


            case "/cart":
                new CartScreen(getRouterService(),getCartService(), session, getProductService()).start(scan);
                break;

            case "/order":
                new OrderScreen();
                break;
            
            default:
                 break;

        }
    }

    /*----------------- Helper Method ---------------- */
    //we are creating this method because in "/register" RegisterScreen will be injected with many dependancies 
    //and instead of listing all new dependencies we will handle it in healper methods 
    private UserService getUserService(){
        return new UserService(new UserDAO(), getRoleService());
    }
    private RoleService getRoleService() {

        return new RoleService(new RoleDAO());
    }

    private RouterService getRouterService() {

        return new RouterService(session);
    }
    private CartService getCartService(){
        return new CartService(new CartItemsDAO(), new CartDAO() );
    }

    private ProductService getProductService() {

        return new ProductService(new ProductDAO());
    }

   

    
}
