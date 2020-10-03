package com.toan.ecommercedemo.model.dto;

import java.io.Serializable;

public class TikiSellerDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private long id;
    private String name;
    private String logo;
    private long store_id;

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

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public long getStore_id() {
        return store_id;
    }

    public void setStore_id(long store_id) {
        this.store_id = store_id;
    }
}
