package com.revature.rhshop.utils;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactory {

    private static ConnectionFactory instance;
    private Connection connection;

    //the ConnectionFaction constructor is private because we want the connection to follow singlton 
    //designe pattern and we can only create one version of this connection and share it
    //ClassNotNound Exception Handles if Driver can not be loaded
    //IOException handles if application.properties is not found
         
    private ConnectionFactory () throws IOException, ClassNotFoundException, SQLException{
        
        //this will check if the driver is installed
        Class.forName("org.postgresql.Driver");

        Properties props = getProperties();

        System.out.println(props.getProperty("url") +  props.getProperty("username") + props.getProperty("password"));

        connection = DriverManager.getConnection(props.getProperty("url"), props.getProperty("username"), props.getProperty("password"));

    }


    /* -----------------> Methods <---------------- */

    //because the above method is private nobody can reach it we created this method to check if instance of the singlton class is alrady 
    // exists or null(if exist return that instance instade of creating new one else if it is null create a new connection  )
    public static ConnectionFactory getInstance() throws ClassNotFoundException, IOException, SQLException {

        if(instance == null || instance.connection.isClosed()){
            return new ConnectionFactory();
            // System.out.println("empty");
        }
        return instance;
        
    }

    public Connection getConnection(){
        return connection;
    }

    /* -----------> Helper Methods <------------- */
    
    private Properties getProperties() throws IOException {
        Properties props = new Properties();

        try(InputStream istream = getClass().getClassLoader().getResourceAsStream("application.properties")) {
            if(istream == null) {
                throw new IOException("unable to find ");
            }
            props.load(istream);

        }

        return props;

    }
}
