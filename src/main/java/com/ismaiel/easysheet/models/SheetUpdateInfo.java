/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ismaiel.easysheet.models;

/**
 *
 * @author Ismaiel
 */
public class SheetUpdateInfo {
    private long id;
    private String password;
    private String viewPassword;
    
    private String newPassword;
    private String newViewPassword;

    public SheetUpdateInfo() {
    }

    public SheetUpdateInfo(long id, String password, String viewPassword, String newPassword, String newViewPassword) {
        this.id = id;
        this.password = password;
        this.viewPassword = viewPassword;
        this.newPassword = newPassword;
        this.newViewPassword = newViewPassword;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getViewPassword() {
        return viewPassword;
    }

    public void setViewPassword(String viewPassword) {
        this.viewPassword = viewPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getNewViewPassword() {
        return newViewPassword;
    }

    public void setNewViewPassword(String newViewPassword) {
        this.newViewPassword = newViewPassword;
    }
    
     
    

}
