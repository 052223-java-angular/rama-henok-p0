package com.revature.rhshop.daos;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import com.revature.rhshop.models.Orders;
import com.revature.rhshop.utils.ConnectionFactory;

public class OrderDAO implements CrudDAO<Orders>{

    @Override
    public void save(Orders obj) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'save'");
    }

    public boolean movingCartItems(Orders orders){
        try(Connection conn = ConnectionFactory.getInstance().getConnection()){

            String sql = "insert into orders (order_id, Product_name, total_cost, order_time, user_id) values (?, ?, ?, CURRENT_TIMESTAMP, ?)";

            try(PreparedStatement ps = conn.prepareStatement(sql)){

                ps.setInt(1, orders.getOrder_id());
                ps.setString(2, orders.getProduct_name());
                ps.setDouble(3, orders.getTotal_cost());
                ps.setString(4, orders.getUser_id());

                if(ps.executeUpdate() > 0){
                    return true;
                }

                return false;

            }
        }catch (ClassNotFoundException e){
            e.printStackTrace();
            throw new RuntimeException("Unable to Find Class");
        }catch(IOException e){
            e.printStackTrace();
            throw new RuntimeException("Unable to Run");

        }catch(SQLException e){
            e.printStackTrace();
            throw new RuntimeException("Unable to access Database");
        }
    }

    @Override
    public void update(String id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public void delete(String id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

    @Override
    public Orders findById(String id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findById'");
    }

    @Override
    public List findAll() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findAll'");
    }
    
}
