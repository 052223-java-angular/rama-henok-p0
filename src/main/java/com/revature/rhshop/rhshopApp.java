package com.revature.rhshop;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

import com.revature.rhshop.services.RouterService;
import com.revature.rhshop.utils.ConnectionFactory;
import com.revature.rhshop.utils.Session;



public class rhshopApp {
  public static void main(String args[]) throws ClassNotFoundException, IOException, SQLException {

     Scanner scan = new Scanner(System.in);

    // Create a new RouterService with a Session
    RouterService router = new RouterService(new Session());

    // Navigate to the "/home" route using the router and scanner
    router.navigate("/home", scan);

    //System.out.println(ConnectionFactory.getInstance().getConnection());

    //router.navigate(("/cart"), scan);
    scan.close();

  }
}