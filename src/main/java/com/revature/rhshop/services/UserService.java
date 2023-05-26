package com.revature.rhshop.services;

import java.util.Optional;

import com.revature.rhshop.daos.UserDAO;
import com.revature.rhshop.models.User;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UserService {
    private final UserDAO userDao;
 
    public boolean usernameValidator(String username){
        return username.matches("^(?=[a-zA-Z0-9._]{8,20}$)(?!.*[_.]{2})[^_.].*[^_.]$");
    }

    public boolean isuniqueUsername(String username){
        Optional<User> userOpt = userDao.findByUsername(username);

        if(userOpt.isEmpty()){
            return true;
        }
        
        return false;
    }


}
