package com.toan.ecommercedemo.model.dto;

import com.toan.ecommercedemo.enums.Gender;
import com.toan.ecommercedemo.enums.Role;

import java.io.Serializable;
import java.util.Date;

public class UpdateUserDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String password;

    private String phone;

    private String name;

    private Date dob;

    private String email;

    private int gender;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }
}
