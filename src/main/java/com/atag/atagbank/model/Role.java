package com.atag.atagbank.model;

import javax.persistence.*;

@Table (name = "roles")
@Entity
public class Role {
    @Id
    @GeneratedValue
    private Long id;

    private String role;

    public Role() {
    }

    public Role(Long id, String role) {
        this.id = id;
        this.role = role;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
