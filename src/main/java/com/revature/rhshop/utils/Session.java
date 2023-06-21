package com.revature.rhshop.utils;

import com.revature.rhshop.models.User;

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
public class Session {
    // 1, 2, 3, 4...... 10000
    private String id;
    private String username;
    private int roleId;


    public void setSession(User user) {
        this.id = user.getUser_id();
        this.username = user.getUser_name();
    }

    public void clearSession() {
        this.id = "";
        this.username = "";
    }
}