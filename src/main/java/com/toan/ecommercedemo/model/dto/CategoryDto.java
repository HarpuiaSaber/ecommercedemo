package com.toan.ecommercedemo.model.dto;

import java.io.Serializable;
import java.util.List;

public class CategoryDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private long id;

    private String name;

    private List<ChildCategoryDto> children;

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

    public List<ChildCategoryDto> getChildren() {
        return children;
    }

    public void setChildren(List<ChildCategoryDto> children) {
        this.children = children;
    }
}
