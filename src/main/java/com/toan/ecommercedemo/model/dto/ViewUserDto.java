package com.toan.ecommercedemo.model.dto;

import com.toan.ecommercedemo.enums.Gender;
import com.toan.ecommercedemo.enums.Role;

import java.io.Serializable;
import java.util.Date;

public class ViewUserDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String username;

    private String phone;

    private String name;

    private String dob;

    private String email;

    private Gender gender;

    private Role role;

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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
