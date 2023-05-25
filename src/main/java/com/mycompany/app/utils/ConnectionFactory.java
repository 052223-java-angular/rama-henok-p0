package com.mycompany.app.utils;

import java.util.Scanner;


public class RouterService {

    public void navigate(String path, Scanner scan) {
        switch (path) {
            case "/home":
                //new HomeScreen(this).start(scan);
                break;
            case "/login":
                break;
            case "/register":
                //new RegisterScreen().start(scan);
                break;
            case "/review":
                break;
            default:
                break;
        }
    }
}
