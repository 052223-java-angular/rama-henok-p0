package com.revature.rhshop.services;

import java.util.Optional;
import com.revature.rhshop.models.Users;
import org.mindrot.jbcrypt.BCrypt;
import com.revature.rhshop.daos.UserDAO;

import lombok.AllArgsConstructor;


@AllArgsConstructor
public class UserService {
    private final UserDAO userDao;

    
    public Users register(String username, String password) {
        
        String hashed = BCrypt.hashpw(password, BCrypt.gensalt());
        Users newUser = new Users(username, hashed);
        userDao.save(newUser);
        return newUser;
    }

    public boolean isValidUsername(String username){

        return username.matches("^(?=[a-zA-Z0-9._]{8,20}$)(?!.*[_.]{2})[^_.].*[^_.]$");
    
    }

    public boolean isUniqueUsername(String username){
        Optional<Users> userOpt = userDao.findByUsername(username);

        if(userOpt.isEmpty()){

            return true;
        }
        return false;
    }

    public Optional<Users> login(String username, String password){

        //query db for presence of username
        Optional<Users> user = userDao.findByUsername(username);

        
        if(user.isEmpty()){
            return Optional.empty();
        }
        return user;
        //boolean isMatched = BCrypt.checkpw(password, user.get().getPassword());

        /*if(isMatched){
                return user;
        }
        return Optional.empty();
        */
    }


    

    public boolean isValidPassword(String username) {
        return username.matches("^(?=[a-zA-Z0-9._]{8,20}$)(?!.*[_.]{2})[^_.].*[^_.]$");
    }

    public boolean isSamePassword(String password, String confirmPassword){

        return password.equals(confirmPassword);
    }
}
