package com.toan.ecommercedemo.model.dto;

import java.io.Serializable;

public class ViewShopDto implements Serializable {

    private static final long serialVersionUID = 1L;
    private Long id;

    private String name;

    private SellerDto seller;

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

    public SellerDto getSeller() {
        return seller;
    }

    public void setSeller(SellerDto seller) {
        this.seller = seller;
    }
}
