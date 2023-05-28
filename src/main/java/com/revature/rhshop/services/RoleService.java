package com.revature.rhshop.services;

import java.util.Optional;

import com.revature.rhshop.daos.RoleDAO;
import com.revature.rhshop.models.Role;
import com.revature.rhshop.utils.customeExceptions.RoleNotFoundException;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class RoleService {
    private final RoleDAO roleDAO;

    public Role findByRolename(String role_name){

        Optional<Role> rolOpt = roleDAO.findByRolename(role_name);


        if(rolOpt.isEmpty()){
            throw new RoleNotFoundException();
        }

        return rolOpt.get();

    }
}
