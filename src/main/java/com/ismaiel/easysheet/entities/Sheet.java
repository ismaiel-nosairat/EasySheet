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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
@JsonIgnoreProperties({"members"})
@Entity
public class Sheet {

    @Id
    @GeneratedValue
    private long id;
    @NotNull
    @Size(min = 2, max = 34)
    private String name;
    private Date date;
    @NotNull
    @Size(min = 2, max = 34)
    private String password;
    @NotNull
    @Size(min = 2, max = 34)
    private String viewPassword;

    @JsonManagedReference
    @OneToMany(mappedBy = "sheet", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Member> members;

    public Sheet(String name, Date date, String adminPassword, String password) {
        this.name = name;
        this.date = date;
        this.password = adminPassword;
        this.viewPassword = password;
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

    public void setPassword(String password) {
        this.password = password;
    }

    public void setViewPassword(String viewPassword) {
        this.viewPassword = viewPassword;
    }

    public void setMembers(List<Member> members) {
        this.members = members;
    }

    public String getPassword() {
        return password;
    }

    public String getViewPassword() {
        return viewPassword;
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
