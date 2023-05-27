package com.revature.rhshop.services;

import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;

import com.revature.rhshop.daos.UserDAO;
import com.revature.rhshop.models.Role;
import com.revature.rhshop.models.User;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UserService {
    private final UserDAO userDao;
    private final RoleService roleService;

    public User Register(String username, String password){

        Role foundRole = roleService.findByRolename("USER");
        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
        User newUser = new User(username, hashedPassword, foundRole.getRole_id());

        userDao.save(newUser);

        return newUser;
    }
 
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

    public boolean passwordValidator(String password){
        return password.matches("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$");
    }
    public boolean passwordConfirmation(String password, String confirmPassword){
        return password.matches(confirmPassword);
    }


}
