
package com.ismaiel.easysheet.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;


@JsonIgnoreProperties({"shares"})
@Entity

public class Member
{
    @Id
    @GeneratedValue
    private long id;
    private String name;
    
    @JsonBackReference(value = "sheet-member")
    @ManyToOne
    @JoinColumn(name = "sheet_id",referencedColumnName = "id")
    private Sheet sheet;
    
    @JsonBackReference(value = "a_name")
    //@JsonManagedReference
    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Share> shares;
    
    
    public Member( String name, Sheet sheet) {
       
        this.name = name;
        this.sheet = sheet;
        this.shares=new ArrayList<>();
    }

    public Member() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Sheet getSheet() {
        return sheet;
    }

    public void setSheet(Sheet sheet) {
        this.sheet = sheet;
    }

    @JsonIgnore
    public List<Share> getShares() {
        return shares;
    }

    public void setShares(List<Share> shares) {
        this.shares = shares;
    }
    

}
