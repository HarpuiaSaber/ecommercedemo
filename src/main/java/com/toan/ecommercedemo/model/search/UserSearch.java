package com.toan.ecommercedemo.model.search;

public class UserSearch extends BaseDateSearch {

    private String key;
    private Integer role;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }
}
