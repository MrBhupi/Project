package com.project.Project.model;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userid;

    private String username;
    private String email;

    @Column(name = "password_hash")
    private String password;

    private String role;

    @Column(name = "requires_password_change")
    private Boolean Pass_change;

    /* Getters and Setters */

    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }

    public Boolean getPass_change() {
        return Pass_change;
    }

    public void setPass_change(Boolean pass_change) {
        Pass_change = pass_change;
    }


}
