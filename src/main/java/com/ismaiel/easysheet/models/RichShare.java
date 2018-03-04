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
public class RichShare {
    private long entryId;
    private String entryName;
    private long shareId;
    private double amount;   

    public RichShare() {
    }

    public RichShare(long entryId, String entryName, long shareId, double amount) {
        this.entryId = entryId;
        this.entryName = entryName;
        this.shareId = shareId;
        this.amount = amount;
    }

    public long getEntryId() {
        return entryId;
    }

    public void setEntryId(long entryId) {
        this.entryId = entryId;
    }

    public String getEntryName() {
        return entryName;
    }

    public void setEntryName(String entryName) {
        this.entryName = entryName;
    }

    public long getShareId() {
        return shareId;
    }

    public void setShareId(long shareId) {
        this.shareId = shareId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

   
    
}
