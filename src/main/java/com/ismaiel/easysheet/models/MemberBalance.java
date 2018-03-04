/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ismaiel.easysheet.models;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Ismaiel
 */
public class MemberBalance{
    public List<RichShare> shares;
    public double balance;
    public MemberBalance() {
        this.shares=new ArrayList<>();
    }

    public MemberBalance(List<RichShare> shares) {
        this.shares = shares;
    }

    public List<RichShare> getShares() {
        return shares;
    }

    public void setShares(List<RichShare> shares) {
        this.shares = shares;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
    
    
}



    
