package com.assenza.alejandro.myauctions.models.Login;

/**
 * Created by alejandro on 09/07/17.
 */

public class User {

    private String username;
    private String password;

    public User(String user, String pass) {
        this.username = user;
        this.password = pass;
    }

    public String GetUsername() { return this.username; }

    public String GetPassword() { return  this.password; }

}
