package com.toan.ecommercedemo.model.dto;

import java.io.Serializable;

public class TikiProductImageDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private String base_url;

    public String getBase_url() {
        return base_url;
    }

    public void setBase_url(String base_url) {
        this.base_url = base_url;
    }
}
