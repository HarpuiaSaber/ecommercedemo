package com.toan.ecommercedemo.model.dto;

import java.io.Serializable;
import java.util.List;

public class ViewCategoryDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String name;

    private List<ViewCategoryDto> children;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ViewCategoryDto> getChildren() {
        return children;
    }

    public void setChildren(List<ViewCategoryDto> children) {
        this.children = children;
    }
}
