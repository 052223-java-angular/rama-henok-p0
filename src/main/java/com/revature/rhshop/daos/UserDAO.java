package com.revature.rhshop.daos;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import com.revature.rhshop.models.User;
import com.revature.rhshop.utils.ConnectionFactory;

public class UserDAO implements CrudDAO<User> {

    @Override
    public void save(User user) {
        
        try(Connection conn = ConnectionFactory.getInstance().getConnection()){

            String sql = "INSERT INTO users (user_id, user_name, password, role_id) VALUES (?, ?, ?, ?)";

            try(PreparedStatement ps = conn.prepareStatement(sql)){

                ps.setString(1, user.getUser_id() );
                ps.setString(2, user.getUser_name());
                ps.setString(3, user.getPassword());
                ps.setInt(4, user.getRole_id());

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
    public User findById(String id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findById'");
    }

    @Override
    public List findAll() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findAll'");
    }

    public Optional<User> findByUsername(String username){
       
        //we use Optional because it will return empty user object rather than null object when there is no data in DB
        // and returning null will result in NullPointerException
        
        try(Connection conn = ConnectionFactory.getInstance().getConnection()){

            String sql = "SELECT * FROM users WHERE user_name = ?";

            try(PreparedStatement ps = conn.prepareStatement(sql)){

                ps.setString(1, username);

                try(ResultSet rs = ps.executeQuery()){
                    if(rs.next()){
                        User user = new User();

                        user.setUser_id((rs.getString("user_id")));
                        user.setUser_name((rs.getString("user_name")));
                        user.setPassword((rs.getString("password")));
                        user.setRole_id((rs.getInt("role_id")));

                        return Optional.of(user);
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

        return Optional.empty();
         
    }
    
}
