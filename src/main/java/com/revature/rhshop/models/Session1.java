
package com.revature.rhshop.models;
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
public class Session1 {
    private String id;
    private String username;
   

    public void setSession(User user) {
        this.id = user.getUser_id();
        this.username = user.getPassword();
    }

    public void clearSession() {
        this.id = "";
        this.username = "";
    }
}