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
public class Users {
    private String userId;
    private String username;
    private String password;
    private String roleId;

    public Users(String username, String password, String roleId) {
        this.userId = UUID.randomUUID().toString();
        this.username = username;
        this.password = password;
        this.roleId = roleId;
    }
}