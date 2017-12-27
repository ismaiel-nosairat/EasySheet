package com.ismaiel.easysheet.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@JsonIgnoreProperties(value={"shares"},allowSetters = true,allowGetters = false)
@Entity
public class Entry {

    @Id
    @GeneratedValue
    private long id;
    private double amount;
    private Date date;
    private String name;

    @ManyToOne
    @JoinColumn(name = "creditor_id")
    private Member creditor;

    @JsonManagedReference(value="entry-share")
    @OneToMany(mappedBy = "entry", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Share> shares;
    
    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "sheet_id",referencedColumnName = "id")
    private Sheet sheet;
    

    public Entry(String name,double amount, Date date, Member creditor ,Sheet sheet) {
        this.name=name;
        this.amount = amount;
        this.date = date;
        this.creditor = creditor;
        this.shares=new ArrayList<Share>();
        this.sheet=sheet;
    }

    public void setSheet(Sheet sheet) {
        this.sheet = sheet;
    }

    public Sheet getSheet() {
        return sheet;
    }

    public Entry() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Member getCreditor() {
        return creditor;
    }

    public void setCreditor(Member creditor) {
        this.creditor = creditor;
    }

    public List<Share> getShares() {
        return shares;
    }

    public void setShares(List<Share> shares) {
        this.shares = shares;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
    
    
}
