package se.ifmo.ru.auth;

import java.io.Serializable;

public class Credentials implements Serializable {

    private String username;
    private String password;

    public Credentials(){

    }
    public Credentials(String username, String password) {
        this.password = password;
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}