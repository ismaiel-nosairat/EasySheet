
package com.ismaiel.easysheet.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


@Entity
public class Share
{
    @Id @GeneratedValue
    private long id;
    
    //remeved cause i need member of share ,not vice
    //@JsonBackReference
    
    //Removed cause we want member of each share
    //@ManyToOne(fetch = FetchType.LAZY)
    
    //removed case it cause 415 response
    //@JsonManagedReference(value = "a_name")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="member_id")
    private Member member;
    
    @JsonBackReference(value="entry-share")
    @ManyToOne
    @JoinColumn(name="entry_id")
    private Entry entry;
    
    private double amount;

    public Share() {
    }

    public Share( Member member, Entry entry, double amount) {
        
        this.member = member;
        this.entry = entry;
        this.amount = amount;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public Entry getEntry() {
        return entry;
    }

    public void setEntry(Entry entry) {
        this.entry = entry;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
    
    
    
}
