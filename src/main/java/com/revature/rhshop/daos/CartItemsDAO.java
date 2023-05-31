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

            String sql = "insert into cartitems (cart_item_id, product_name, price, quantity, cart_id, product_id, user_id ) values (?, ?, ?, ?, ?, ?, ?);";

            try(PreparedStatement ps = conn.prepareStatement(sql)){

                ps.setString(1, items.getCart_item_id());
                ps.setString(2, items.getProduct_name());
                ps.setFloat(3, items.getPrice());
                ps.setInt(4, items.getQuantity());
                ps.setInt(5, items.getCart_id());
                ps.setInt(6, items.getProduct_id());
                ps.setString(7, items.getUser_id());

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
                        item.setUser_id(rs.getString("user_id"));
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

    public String findByProductName(String user_id, String product_name) {

        try(Connection conn = ConnectionFactory.getInstance().getConnection()){

            String sql = "SELECT * FROM cartitems WHERE user_id = ? AND product_name = ?";

            try(PreparedStatement ps = conn.prepareStatement(sql)){

                ps.setString(1, user_id);
                ps.setString(2, product_name);

                try(ResultSet rs = ps.executeQuery()){
                    if(rs.next()){
                        return rs.getString("cart_item_id");
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

        return "";

        
    }


    public List<CartItems> findAllByUser(String user_id) {

        List<CartItems> cartItemsList = new ArrayList<CartItems>();

        
        try(Connection conn = ConnectionFactory.getInstance().getConnection()){

            String sql = "SELECT * FROM cartitems WHERE user_id = ?";

            try(PreparedStatement ps = conn.prepareStatement(sql)){
               
                ps.setString(1, user_id);

                try(ResultSet rs = ps.executeQuery()){
                    while(rs.next()){
                        CartItems item = new CartItems();

                        item.setCart_item_id(rs.getString("cart_item_id"));
                        item.setProduct_name(rs.getString("product_name"));;
                        item.setPrice(rs.getFloat("price"));
                        item.setQuantity(rs.getInt("quantity"));
                        item.setCart_id(rs.getInt("cart_id"));
                        item.setProduct_id(rs.getInt("product_id"));
                        item.setUser_id(rs.getString("user_id"));
                        
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
    


    public int updateQuantity(String id, int quantity, String user_id) {

        try(Connection conn = ConnectionFactory.getInstance().getConnection()){

            String sql = "UPDATE cartitems SET quantity = ?  WHERE cart_item_id = ? AND user_id = ?";

            try(PreparedStatement ps = conn.prepareStatement(sql)){

                ps.setInt(1, quantity);
                ps.setString(2, id);
                ps.setString(3, user_id);

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

    
    public boolean deleteById(String cart_item_id, String user_id) {

        try(Connection conn = ConnectionFactory.getInstance().getConnection()){

            String sql = "DELETE FROM cartitems WHERE cart_item_id = ? AND user_id = ?";

            try(PreparedStatement ps = conn.prepareStatement(sql)){

                ps.setString(1, cart_item_id);
                ps.setString(2, user_id);

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


    public double priceCalculator(String user_id) {
        double total = 0.00;

        try(Connection conn = ConnectionFactory.getInstance().getConnection()){

            //AS total refers to an alias which is used to store the calculated result of the sql statment
            String sql = "SELECT SUM(quantity * price) AS total FROM cartitems where user_id = ?";

            try(PreparedStatement ps = conn.prepareStatement(sql)){
                
                ps.setString(1, user_id);

                try(ResultSet rs = ps.executeQuery()){
                    if (rs.next()) {
                        total = rs.getFloat("total");
            }

                    return total;
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


    public int celarCart(String user_id) {

        try(Connection conn = ConnectionFactory.getInstance().getConnection()){

            //AS total refers to an alias which is used to store the calculated result of the sql statment
            String sql = "DELETE FROM cartitems WHERE user_id = ?";

            try(PreparedStatement ps = conn.prepareStatement(sql)){

                ps.setString(1, user_id);

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
    }


    @Override
    public List<CartItems> findAll() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findAll'");
    }


    public CartItems findAllOrders(String user_id) {
        try(Connection conn = ConnectionFactory.getInstance().getConnection()){

            CartItems item = new CartItems();

            String sql = "SELECT * FROM cartitems WHERE user_id = ?";

            try(PreparedStatement ps = conn.prepareStatement(sql)){
               
                ps.setString(1, user_id);

                try(ResultSet rs = ps.executeQuery()){
                    while(rs.next()){
                        

                        item.setCart_item_id(rs.getString("cart_item_id"));
                        item.setProduct_name(rs.getString("product_name"));;
                        item.setPrice(rs.getFloat("price"));
                        item.setQuantity(rs.getInt("quantity"));
                        item.setCart_id(rs.getInt("cart_id"));
                        item.setProduct_id(rs.getInt("product_id"));
                        item.setUser_id(rs.getString("user_id"));
                        
                    }

                    return item;
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
     
}