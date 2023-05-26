package com.revature.rhshop.services;

import java.util.Scanner;

import com.revature.rhshop.daos.UserDAO;
import com.revature.rhshop.screens.HomeScreen;
import com.revature.rhshop.screens.RegisterScreen;

public class RouterService {
    public void navigate(String path, Scanner scan){
        switch(path) {

            case "/home":
                //this refers to the class itself(RouterService) because we injected RouterService dependency inside homescreen
                new HomeScreen(this).start(scan);  
            break;

            case "login":
            break;

            case "/register":
            new RegisterScreen(getUserService()).start(scan);
            break;
            
            default:
            break;

        }
    }

    /*----------------- Helper Method ---------------- */
    //we are creating this method because in "/register" RegisterScreen will be injected with many dependancies 
    //and instead of listing all new dependencies we will handle it in healper methods 
    private UserService getUserService(){
        return new UserService(new UserDAO());
    }
}
