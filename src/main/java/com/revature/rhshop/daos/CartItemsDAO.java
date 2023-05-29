package com.revature.rhshop.daos;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public CartItems findById(String cart_item_id) {

        try(Connection conn = ConnectionFactory.getInstance().getConnection()){

            String sql = "SELECT * FROM cartitems WHERE cart_item_id = ?";

            try(PreparedStatement ps = conn.prepareStatement(sql)){

                ps.setString(1, cart_item_id);

                try(ResultSet rs = ps.executeQuery()){
                    if(rs.next()){
                        CartItems item = new CartItems();

                        item.setProduct_name(rs.getString("product_name"));;
                        item.setPrice(rs.getFloat("price"));
                        item.setQuantity(rs.getInt("quantity"));
                        item.setCart_id(rs.getInt("cart_id"));
                        item.setProduct_id(rs.getInt("product_id"));
                        return item;
                    }
                }
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

        return null;

        
    }

    @Override
    public List<CartItems> findAll() {

        List<CartItems> cartItemsList = new ArrayList<CartItems>();

        
        try(Connection conn = ConnectionFactory.getInstance().getConnection()){

            String sql = "SELECT * FROM cartitems";

            try(PreparedStatement ps = conn.prepareStatement(sql)){

                try(ResultSet rs = ps.executeQuery()){
                    while(rs.next()){
                        CartItems item = new CartItems();

                        item.setProduct_name(rs.getString("product_name"));;
                        item.setPrice(rs.getFloat("price"));
                        item.setQuantity(rs.getInt("quantity"));
                        item.setCart_id(rs.getInt("cart_id"));
                        item.setProduct_id(rs.getInt("product_id"));
                        
                        cartItemsList.add(item);
                    }

                    return cartItemsList;
                }
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

    
    public boolean deleteById(String cart_item_id) {

        try(Connection conn = ConnectionFactory.getInstance().getConnection()){

            String sql = "DELETE FROM cartitems WHERE cart_item_id = ?";

            try(PreparedStatement ps = conn.prepareStatement(sql)){

                ps.setString(1, cart_item_id);

                int rows = ps.executeUpdate();

                if(rows > 0){

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
     
}
