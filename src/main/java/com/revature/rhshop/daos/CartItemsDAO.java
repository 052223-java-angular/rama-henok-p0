package com.revature.rhshop.daos;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import com.revature.rhshop.models.CartItems;
import com.revature.rhshop.utils.ConnectionFactory;

public class CartItemsDAO implements CrudDAO<CartItems> {

    @Override
    public void save(CartItems items) {
        try(Connection conn = ConnectionFactory.getInstance().getConnection()){

            String sql = "insert into cartitems (cart_item_id, product_name, price, quantity, cart_id, product_id ) values (default, ?, ?, ?, ?, ?);";

            try(PreparedStatement ps = conn.prepareStatement(sql)){

                ps.setString(1, items.getProduct_name());
                ps.setFloat(2, items.getPrice());
                ps.setInt(3, items.getQuantity());
                ps.setInt(4, items.getCart_id());
                ps.setInt(5, items.getProduct_id());

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
