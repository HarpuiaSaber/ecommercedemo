package com.toan.ecommercedemo.model.dto;

import java.io.Serializable;

public class SaveShopDto implements Serializable {

    private static final long serialVersionUID = 1L;
    private Long id;

    private String name;

    private Long sellerId;

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

    public Long getSellerId() {
        return sellerId;
    }

    public void setSellerId(Long sellerId) {
        this.sellerId = sellerId;
    }
}
