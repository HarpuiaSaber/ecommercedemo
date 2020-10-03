package com.toan.ecommercedemo.model.dto;

import java.io.Serializable;
import java.util.List;

public class TikiSpecificationDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private long id;
    private String name;
    private List<TikiAttributeDto> attributes;

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

    public List<TikiAttributeDto> getAttributes() {
        return attributes;
    }

    public void setAttributes(List<TikiAttributeDto> attributes) {
        this.attributes = attributes;
    }
}
