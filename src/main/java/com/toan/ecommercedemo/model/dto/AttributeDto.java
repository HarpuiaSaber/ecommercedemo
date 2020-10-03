package com.toan.ecommercedemo.model.dto;

import com.toan.ecommercedemo.entities.Specification;

import java.io.Serializable;

public class AttributeDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String name;

    private String value;

    private Long specificationId;

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

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Long getSpecificationId() {
        return specificationId;
    }

    public void setSpecificationId(Long specificationId) {
        this.specificationId = specificationId;
    }
}
