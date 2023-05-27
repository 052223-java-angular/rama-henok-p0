package com.revature.rhshop.daos;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import com.revature.rhshop.models.Role;
import com.revature.rhshop.utils.ConnectionFactory;

public class RoleDAO  implements CrudDAO{

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

    public Optional<Role> findByRolename(String role_name){
       
        //we use Optional because it will return empty user object rather than null object when there is no data in DB
        // and returning null will result in NullPointerException
        
        try(Connection conn = ConnectionFactory.getInstance().getConnection()){

            String sql = "SELECT * FROM roles WHERE role_name = ?";

            try(PreparedStatement ps = conn.prepareStatement(sql)){

                ps.setString(1, role_name);

                try(ResultSet rs = ps.executeQuery()){
                    if(rs.next()){
                        Role role = new Role();

                        role.setRole_id(rs.getInt("role_id"));
                        role.setRole_name(rs.getString("role_name"));
                      
                        return Optional.of(role);
                    }
                }
            }
            
        }catch (ClassNotFoundException e){
            throw new RuntimeException("Unable to Find Class");
        }catch(IOException e){
            throw new RuntimeException("Unable to Run");

        }catch(SQLException e){
            throw new RuntimeException("Unable to access Database");
        }


        return Optional.empty(); 
    }
    
}
