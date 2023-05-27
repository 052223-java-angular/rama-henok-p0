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

/**
 * The UserDAO class handles database operations for User objects.
 * It implements the CrudDAO interface.
 */
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
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public void delete(String id) {
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

    @Override
    public User findById(String id) {
        throw new UnsupportedOperationException("Unimplemented method 'findById'");
    }

    @Override
    public List<User> findAll() {
        throw new UnsupportedOperationException("Unimplemented method 'findAll'");
    }

    /**
     * Finds a user by their username.
     *
     * @param username the username of the user to find
     * @return an Optional containing the User object if found, otherwise an empty
     *         Optional
     */
    public Optional<User> findByUsername(String username) {
        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
            String sql = "SELECT * FROM users WHERE user_name = ?";
            
            System.out.println("username is " + username);
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                // Set the username parameter for the prepared statement
                ps.setString(1, username);

                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        // Create a new User object and populate it with data from the result set
                        User user = new User();
                        String test = rs.getString("user_id");
                        System.out.println("test is " + test);
                        user.setUser_id( rs.getString("user_id") );
                        user.setUser_name(rs.getString("user_name"));
                        user.setPassword(rs.getString("password"));
                        user.setRole_id(rs.getInt("role_id"));
                        return Optional.of(user);
                    }
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Unable to connect to the database", e);
        } catch (IOException e) {
            throw new RuntimeException("Cannot find application.properties", e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Unable to load JDBC driver", e);
        }

        return Optional.empty();
    }
}