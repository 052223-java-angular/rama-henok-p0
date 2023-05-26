package com.revature.rhshop.utils;

import com.revature.rhshop.models.Users;

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
    private String roleId;

    public void setSession(Users user) {
        this.id = user.getUserId();
        this.username = user.getUsername();
        this.roleId = user.getRoleId();
    }
}