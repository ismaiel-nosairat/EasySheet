
package com.ismaiel.easysheet.models;


public class Login
{
    private long id;
    private String password;

    public Login() {
    }

    public Login(long id, String name) {
        this.id = id;
        this.password = name;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
}
