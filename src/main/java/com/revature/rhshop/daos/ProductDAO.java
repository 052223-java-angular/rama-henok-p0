package com.revature.rhshop.daos;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;
import java.util.Optional;

import com.revature.rhshop.models.Products;
import com.revature.rhshop.utils.ConnectionFactory;

public class ProductDAO implements CrudDAO<Products> {
    @Override
    public List<Products> findAll() {
        List<Products> productList = new ArrayList<Products>();
        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
            Statement s = conn.createStatement();
            try(ResultSet rs = s.executeQuery("select * from products")) {
                while(rs.next()) {
                    Products product = 
                    new Products(rs.getInt("product_id"), rs.getString("product_name"),  rs.getFloat("price"), rs.getInt("stock"), rs.getString("category_name"));
                    productList.add(product);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        } catch (IOException e) {
            System.out.println("couldn't open db.properties");
            throw new RuntimeException(e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println("couldn't find postgres driver for jdbc");
            throw new RuntimeException(e.getMessage());
        }

        return productList;
    }

    @Override
    public void save(Products obj) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'save'");
    }
    

    @Override
    public void update(String id ) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public void delete(String id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

    @Override
    public Products findById(String id) {
        return  new Products();
    }

    public List<Products> findByName(String name) {
        List<Products> products = new ArrayList<Products>();

        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
            String sql = "select * from products  where product_name iLIKE ? ORDER BY product_name";

            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, "%" + name + "%");

                try( ResultSet rs = ps.executeQuery() ) {
                    while(rs.next() ) {
                         // Create a new User object and populate it with data from the result set
                        products.add( new Products(
                            rs.getInt("product_id"), 
                            rs.getString("product_name"),  
                            rs.getFloat("price"), 
                            rs.getInt("stock"),
                            rs.getString("category_name"))) ;  
                       
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        } catch (IOException e) {
            System.out.println("couldn't open db.properties");
            throw new RuntimeException(e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println("couldn't find postgres driver for jdbc");
            throw new RuntimeException(e.getMessage());
        }

        return products;
    }

    public List<String> getAllCategories() {
        List<String> categoriesList = new ArrayList<String>();
        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
            String sql = "select distinct category_name from products";

            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                try(ResultSet rs = ps.executeQuery()) {
                    while(rs.next()) {
                        categoriesList.add(rs.getString("category_name"));
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        } catch (IOException e) {
            System.out.println("couldn't open db.properties");
            throw new RuntimeException(e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println("couldn't find postgres driver for jdbc");
            throw new RuntimeException(e.getMessage());
        }

        return categoriesList;
    }

    public List<Products> findByCategory(String category_name) {
        List<Products> productList = new ArrayList<Products>();
       
        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
            String sql = "select * from products where category_name ilike ? order by category_name";
            System.out.println("sql " + sql);

            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, "%" + category_name + "%");
                //ps.setString(1, name);

                try(ResultSet rs = ps.executeQuery()) {
                    while(rs.next()) {
                        productList.add(new Products(
                            rs.getInt("product_id"), 
                            rs.getString("product_name"),  
                            rs.getFloat("price"), 
                            rs.getInt("stock"),
                            rs.getString("category_name")));
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        } catch (IOException e) {
            System.out.println("couldn't open db.properties");
            throw new RuntimeException(e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println("couldn't find postgres driver for jdbc");
            throw new RuntimeException(e.getMessage());
        }

        return productList;
    }



    



    

}//endOfClass

