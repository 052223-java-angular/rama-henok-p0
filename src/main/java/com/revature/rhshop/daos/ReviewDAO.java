package com.revature.rhshop.daos;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.ArrayList;
import java.util.Optional;
import java.sql.Statement;



import com.revature.rhshop.models.Reviews;
import com.revature.rhshop.utils.ConnectionFactory;


public class ReviewDAO implements CrudDAO<Reviews> {

    @Override
    public void save(Reviews obj) {
        //save review;
        
    }

    @Override
    public void update(String id) {
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public void delete(String string) {
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

    @Override
    public Reviews findById(String id) {
        return  new Reviews();
    }

  
    public List<Reviews> getReviews(String product_id){
        List<Reviews> reviews = new ArrayList<Reviews>();
        return  reviews;
       
    }

    @Override
    public List<Reviews> findAll() {
        List<Reviews> revList = new ArrayList<Reviews>();
        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
            Statement s = conn.createStatement();
            try(ResultSet rs = s.executeQuery("select * from reviews")) {

                while(rs.next()) {
                    Reviews reviews = new Reviews(rs.getInt("review_id"), rs.getInt("rating"),
                        rs.getString("comment"), rs.getString("user_id"), rs.getInt("product_id"));
                        revList.add(reviews);
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

        return revList;
    }

    public List<Reviews> getReviewsByProductName(String name){
        List<Reviews> revList = new ArrayList<Reviews>();
        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
            Statement s = conn.createStatement();
            try(ResultSet rs = s.executeQuery("select * from reviews")) {

                while(rs.next()) {
                    Reviews reviews = new Reviews(rs.getInt("review_id"), rs.getInt("rating"),
                        rs.getString("comment"), rs.getString("user_id"), rs.getInt("product_id"));
                        revList.add(reviews);
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

        return revList;
       
    }

}


