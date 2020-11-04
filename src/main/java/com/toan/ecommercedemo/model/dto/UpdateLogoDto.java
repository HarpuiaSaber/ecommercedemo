package com.toan.ecommercedemo.model.dto;

import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;

public class UpdateLogoDto implements Serializable {

    private static final long serialVersionUID = 1L;
    private Long id;

    private MultipartFile logo;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public MultipartFile getLogo() {
        return logo;
    }

    public void setLogo(MultipartFile logo) {
        this.logo = logo;
    }
}
