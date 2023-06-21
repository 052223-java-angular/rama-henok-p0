package com.revature.rhshop.daos;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import com.revature.rhshop.models.CartItems;
import com.revature.rhshop.utils.ConnectionFactory;

public class CartItemsDAO implements CrudDAO<CartItems> {

    @Override
    public void save(CartItems items) {
        try(Connection conn = ConnectionFactory.getInstance().getConnection()){

            String sql = "insert into cartitems (cart_item_id, product_name, price, quantity, cart_id, product_id ) values (?, ?, ?, ?, ?, ?);";

            try(PreparedStatement ps = conn.prepareStatement(sql)){

                ps.setString(1, items.getCart_item_id());
                ps.setString(2, items.getProduct_name());
                ps.setFloat(3, items.getPrice());
                ps.setInt(4, items.getQuantity());
                ps.setInt(5, items.getCart_id());
                ps.setInt(6, items.getProduct_id());

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
    public CartItems findById(String id) {
 
        return null;
    }

    @Override
    public List<CartItems> findAll() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findAll'");
    }
    


    public int updateQuantity(String id, int quantity) {

        try(Connection conn = ConnectionFactory.getInstance().getConnection()){

            String sql = "UPDATE cartitems SET quantity = ?  WHERE cart_item_id = ?";

            try(PreparedStatement ps = conn.prepareStatement(sql)){

                ps.setInt(1, quantity);
                ps.setString(2, id);

               return ps.executeUpdate();
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

        // return true;
    }
     
}
