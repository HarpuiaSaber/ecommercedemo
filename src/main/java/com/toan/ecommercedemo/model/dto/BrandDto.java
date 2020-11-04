package com.toan.ecommercedemo.model.dto;

import java.io.Serializable;

public class BrandDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String name;

    private Long totalProduct;

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

    public Long getTotalProduct() {
        return totalProduct;
    }

    public void setTotalProduct(Long totalProduct) {
        this.totalProduct = totalProduct;
    }
}
