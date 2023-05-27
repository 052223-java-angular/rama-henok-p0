package com.revature.rhshop.daos;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import com.revature.rhshop.models.Users;
import com.revature.rhshop.utils.ConnectionFactory;

/**
 * The UserDAO class handles database operations for User objects.
 * It implements the CrudDAO interface.
 */
public class UserDAO implements CrudDAO<Users> {

    @Override
    public void save(Users obj) {
        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
            String sql = "INSERT INTO users (user_id, username, password, role_id) VALUES (?, ?, ?, ?)";

            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                // Set values for prepared statement parameters
                ps.setString(1, obj.getUserId());
                ps.setString(2, obj.getUsername());
                ps.setString(3, obj.getPassword());
                ps.setString(4, obj.getRoleId());

                // Execute the SQL statement
                ps.executeUpdate();
            }

        } catch (SQLException e) {
            throw new RuntimeException("Unable to connect to the database", e);
        } catch (IOException e) {
            throw new RuntimeException("Cannot find application.properties", e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Unable to load JDBC driver", e);
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
    public Users findById(String id) {
        throw new UnsupportedOperationException("Unimplemented method 'findById'");
    }

    @Override
    public List<Users> findAll() {
        throw new UnsupportedOperationException("Unimplemented method 'findAll'");
    }

    /**
     * Finds a user by their username.
     *
     * @param username the username of the user to find
     * @return an Optional containing the User object if found, otherwise an empty
     *         Optional
     */
    public Optional<Users> findByUsername(String username) {
        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
            String sql = "SELECT * FROM users WHERE user_name = ?";
            
            System.out.println("username is " + username);
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                // Set the username parameter for the prepared statement
                ps.setString(1, username);

                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        // Create a new User object and populate it with data from the result set
                        Users user = new Users();
                        String test = rs.getString("user_id");
                        System.out.println("test is " + test);
                        user.setUserId(rs.getString("user_id"));
                        user.setUsername(rs.getString("user_name"));
                        user.setPassword(rs.getString("password"));
                        user.setRoleId(rs.getString("role_id"));
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