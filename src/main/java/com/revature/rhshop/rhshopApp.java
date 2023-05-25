package com.revature.rhshop;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

import com.revature.rhshop.services.RouterService;
import com.revature.rhshop.utils.ConnectionFaction;

public class rhshopApp {
  public static void main(String args[]) throws ClassNotFoundException, IOException, SQLException {
    Scanner scan = new Scanner(System.in);
    // RouterService router = new RouterService();

    System.out.println(ConnectionFaction.getInstance().getConnection());

    // router.navigate("/home", scan);


    scan.close();

  }
}
