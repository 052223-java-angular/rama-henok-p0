package com.revature.rhshop.daos;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Random;

import com.revature.rhshop.models.CartItems;
import com.revature.rhshop.models.OrderItems;
import com.revature.rhshop.utils.ConnectionFactory;

public class OrderItemsDAO implements CrudDAO<OrderItems>{

    @Override
    public void save(OrderItems orders) {
        try(Connection conn = ConnectionFactory.getInstance().getConnection()){

            String sql = "insert into orderitems (order_item_id, quantity, price, order_id, product_id) values (?, ?, ?, ?, ?)";

            try(PreparedStatement ps = conn.prepareStatement(sql)){

                ps.setInt(1, orders.getOrder_item_id() );
                ps.setInt(2, orders.getQuantity());
                ps.setFloat(3, orders.getPrice());
                ps.setInt(4, orders.getOrder_id());
                ps.setInt(5, orders.getProduct_id());

                ps.executeUpdate();
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
    public OrderItems findById(String id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findById'");
    }

    @Override
    public List<OrderItems> findAll() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findAll'");
    }

    public boolean movingCartItems(CartItems orders, int order_id) {
        try(Connection conn = ConnectionFactory.getInstance().getConnection()){

            String sql = "insert into orderitems (order_item_id, quantity,price, order_id, product_id) values (?, ?, ?, ?, ?)";

            try(PreparedStatement ps = conn.prepareStatement(sql)){

                Random random = new Random();

                ps.setInt(1, random.nextInt() );
                ps.setInt(2, orders.getQuantity());
                ps.setDouble(3, orders.getPrice());
                ps.setInt(4, order_id);
                ps.setInt(5, orders.getProduct_id());

                ps.executeUpdate();
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
        return false;
    }
    
}
