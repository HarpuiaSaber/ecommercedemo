package com.toan.ecommercedemo.model.dto;

import java.io.Serializable;

public class CommentProductDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private long id;

    private String name;

    private String image;

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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
