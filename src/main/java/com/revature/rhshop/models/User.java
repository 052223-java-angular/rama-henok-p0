package com.revature.rhshop.models;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class User {
    private String user_id;
    private String user_name;
    private String password;
    private String role_id;

    public User(String user_name, String password, String role_id){
        this.user_id = UUID.randomUUID().toString();
        this.user_name = user_name;
        this.password = password;
        this.role_id = role_id;
    }
    
    
}
