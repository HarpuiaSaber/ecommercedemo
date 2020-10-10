package com.toan.ecommercedemo.model.dto;

import java.io.Serializable;

public class ProductImageDto implements Serializable {
    private static final long serialVersionUID = 1L;

    private long id;

    private String path;

    private boolean fromTiki;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public boolean isFromTiki() {
        return fromTiki;
    }

    public void setFromTiki(boolean fromTiki) {
        this.fromTiki = fromTiki;
    }
}
