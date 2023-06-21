package com.revature.rhshop.utils.customeExceptions;

public class RoleNotFoundException extends RuntimeException {

    public RoleNotFoundException(){
        super("Role Not Found");
    }
    
}
