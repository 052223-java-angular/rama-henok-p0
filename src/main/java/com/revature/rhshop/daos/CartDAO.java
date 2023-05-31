package com.revature.rhshop.daos;

import java.io.IOException;
import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.List;

import com.revature.rhshop.models.Carts;
import com.revature.rhshop.utils.ConnectionFactory;

public class CartDAO  implements CrudDAO<Carts> {

    @Override
    public void save(Carts obj) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'save'");
    }

    
    public Carts saveCart(Carts item) {
        Carts cart = new Carts();
        try(Connection conn = ConnectionFactory.getInstance().getConnection()){

            String sql = "insert into carts( cart_id, user_id ) values (default, ?)";

            //System.out.println("ps " + item.getUser_id());

            try(PreparedStatement ps = conn.prepareStatement( sql, PreparedStatement.RETURN_GENERATED_KEYS)){
                ps.setString(1, item.getUser_id() );
                ps.executeUpdate();

                ResultSet rs = ps.getGeneratedKeys();
                if(rs.next()){

                    int cart_id = rs.getInt(1);
                    cart.setCart_id(cart_id);
                    cart.setUser_id(item.getUser_id());
                    return cart;
        
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
    public Carts findById(String id) {
        Carts cart = new Carts();
        
        try(Connection conn = ConnectionFactory.getInstance().getConnection()){

            String sql = "select * from carts where user_id=?";
           
           try ( PreparedStatement ps = conn.prepareStatement(sql) ) {
                ps.setString(1, id);
                
                try(ResultSet rs = ps.executeQuery()){
                    while(rs.next()) {
                        cart = new Carts(rs.getInt("cart_id"), rs.getString("user_id"));  
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
        return cart;
    }

    @Override
    public List<Carts> findAll() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findAll'");
    }
    

    public int updateQuantity(String id, int quantity) {
        return 1;
    }

    
}
