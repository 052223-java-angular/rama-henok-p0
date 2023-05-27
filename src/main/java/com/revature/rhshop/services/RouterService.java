package com.revature.rhshop.services;

import java.util.Scanner;

import com.revature.rhshop.screens.HomeScreen;
import com.revature.rhshop.screens.RegisterScreen;
import com.revature.rhshop.screens.LoginScreen;
import com.revature.rhshop.screens.MenuScreen;


import com.revature.rhshop.daos.RoleDAO;
import com.revature.rhshop.daos.UserDAO;

import com.revature.rhshop.utils.Session;

import lombok.AllArgsConstructor;

/**
 * The RouterService class handles navigation in the rhshop Application.
 */
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
            //new RegisterScreen(this, getUserService(),  session).start(scan);
                new RegisterScreen().start(scan);
            break;

            case "/menu":
                new MenuScreen(session).start(scan);
                break;
            
            default:
            break;

        }
    }

    /* ----------------- Helper Method ------------------------- */ 

    private UserService getUserService(){

        return new UserService(new UserDAO());
    }
    
}