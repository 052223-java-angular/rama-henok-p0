package com.revature.rhshop.services;

import java.util.Scanner;
import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;

import com.revature.rhshop.daos.UserDAO;
import com.revature.rhshop.models.User;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UserService {
    private final UserDAO userDAO;

    public User register(String username, String password) {
        String hashed = BCrypt.hashpw(password, BCrypt.gensalt());
        User newUser = new User(username, hashed);
        userDAO.save(newUser);
        return newUser;
    }

    public boolean isUniqueUsername(String username) {
        Optional<User> userOpt = userDAO.findByUsername(username);

        return userOpt.isEmpty();
    }

    public Optional<User> login(String username, String password) {
        
        Optional<User> user = userDAO.findByUsername(username);
        if (user.isEmpty()) {
            return Optional.empty();
        }
        return user;
       /* boolean isMatching = BCrypt.checkpw(password, user.get().getPassword());
        if (isMatching) {
            return user;
        }
        else {
            return Optional.empty();
        } */
    }
}
