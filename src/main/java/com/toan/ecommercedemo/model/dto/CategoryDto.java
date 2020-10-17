package com.toan.ecommercedemo.model.dto;

import java.io.Serializable;
import java.util.List;

public class CategoryDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private long id;

    private String name;

    private Long parentId;

    private boolean isLeaf;

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

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public boolean isLeaf() {
        return isLeaf;
    }

    public void setLeaf(boolean leaf) {
        isLeaf = leaf;
    }
}
