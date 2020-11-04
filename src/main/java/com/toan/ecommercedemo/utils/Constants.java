package com.toan.ecommercedemo.utils;

import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

public interface Constants {
    public static final String baseUrl = ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString();
    public static final String folderImage = "/images";
    public static final String folderProduct = "/product";
    public static final String folderComment = "/comment";
    public static final String folderShop = "/shop";
    public static final String UPLOAD_FOLDER = "/home/harpuiasaber/ecommercedemo/src/main/resources/static";
}
