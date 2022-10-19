package com.examly.springapp.model;

import java.io.Serializable;
import java.util.*;

import javax.persistence.*;
import javax.validation.constraints.*;
@Entity
@Table(name = "users", uniqueConstraints = {
        @UniqueConstraint(columnNames = "username"),
        @UniqueConstraint(columnNames = "email")
})
public class User implements Serializable{
    private static final long serialVersionUID = -2517851941873251699L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    @Size(max = 20)
    @Column(length = 20)
    private String username;
    @NotBlank
    @Column(length = 50)
    @Email(message = "Email should be valid")
    private String email;
    @NotBlank
    @Size(max = 120)
    private String password;
    //--------------------------------------------------------------
    @Digits(message="Number should contain 10 digits.", fraction = 0, integer = 10) 
    //@Pattern(regexp = "(\\+61|0)[0-9]{9}")
    private String mobilenum;
    //--------------------------------------------------------------
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();
    //private String role;

    public User() {
    }

    public User(String username, String email, String password,String mobilenum) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.mobilenum=mobilenum;
    }
    
    // public User(String username, String email, String password,Set<Role> roles) {
    //     this.username = username;
    //     this.email = email;
    //     this.password = password;
    //     this.roles=roles;
    // }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public String getMobilenum() {
        return this.mobilenum;
    }

    public void setMobilenum(String mobilenum) {
        this.mobilenum = mobilenum;
    }

    // public String getRole() {
    //     return this.role;
    // }

    // public void setRole(String role) {
    //     this.role = role;
    // }
}
