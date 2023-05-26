package com.revature.rhshop.services;

import java.util.Scanner;

import com.revature.rhshop.screens.HomeScreen;
import com.revature.rhshop.screens.RegisterScreen;

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
            case "login":
            break;
            case "/register":
                new RegisterScreen().start(scan);
            break;
            default:
            break;

        }
    }
    
}