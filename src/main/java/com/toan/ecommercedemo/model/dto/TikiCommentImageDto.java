package com.toan.ecommercedemo.model.dto;

import java.io.Serializable;

public class TikiCommentImageDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private String full_path;

    public String getFull_path() {
        return full_path;
    }

    public void setFull_path(String full_path) {
        this.full_path = full_path;
    }
}
