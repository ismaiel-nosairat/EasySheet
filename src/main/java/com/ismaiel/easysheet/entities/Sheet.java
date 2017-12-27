package com.ismaiel.easysheet.entities;

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
import javax.persistence.OneToMany;
@JsonIgnoreProperties({"members"})
@Entity
public class Sheet {

    @Id
    @GeneratedValue
    private long id;
    private String name;
    private Date date;
    private String adminPassword;
    private String password;

    @JsonManagedReference
    @OneToMany(mappedBy = "sheet", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Member> members;

    public Sheet(String name, Date date, String adminPassword, String password) {
        this.name = name;
        this.date = date;
        this.adminPassword = adminPassword;
        this.password = password;
        this.members = new ArrayList<>();
    }

    public Sheet() {
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Date getDate() {
        return date;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setAdminPassword(String adminPassword) {
        this.adminPassword = adminPassword;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setMembers(List<Member> members) {
        this.members = members;
    }

    public String getAdminPassword() {
        return adminPassword;
    }

    public String getPassword() {
        return password;
    }

    public List<Member> getMembers() {
        return members;
    }
//    
//    public void AddMember(Member m){
//        this.members.add(m);
//    }
//    public void removeMember(Member m){
//        this.members.add(m);
//    }
//    

}
