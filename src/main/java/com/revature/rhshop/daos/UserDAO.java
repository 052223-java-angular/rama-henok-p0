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

public class UserDAO implements CrudDAO {

    @Override
    public void save(Object obj) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'save'");
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
    public Object findById(String id) {
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

            String sql = "SELECT * FROM users WHERE username = ?";

            try(PreparedStatement ps = conn.prepareStatement(sql)){

                ps.setString(1, username);

                try(ResultSet rs = ps.executeQuery()){
                    if(rs.next()){
                        User user = new User();

                        user.setUser_id((rs.getString("user_id")));
                        user.setUser_name((rs.getString("user_name")));
                        user.setPassword((rs.getString("password")));
                        user.setRole_id((rs.getString("role_id")));

                        return Optional.of(user);
                    }
                }
            }
            
        }catch (SQLException | ClassNotFoundException | IOException e){
            throw new RuntimeException("Unable to access Database");
        }
        return Optional.empty(); 


    }
    
}
