package com.abc.agrios;

public class LoginRequestF {
    private String username;
    private String password;

    public LoginRequestF(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
