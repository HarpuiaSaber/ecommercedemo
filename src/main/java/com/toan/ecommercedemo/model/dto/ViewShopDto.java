package com.toan.ecommercedemo.model.dto;

import java.io.Serializable;

public class ViewShopDto implements Serializable {

    private static final long serialVersionUID = 1L;
    private Long id;

    private String logo;

    private String name;

    private String createdDate;

    private long totalComment;

    private long totalProduct;

    private double rating;

    private String description;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public long getTotalComment() {
        return totalComment;
    }

    public void setTotalComment(long totalComment) {
        this.totalComment = totalComment;
    }

    public long getTotalProduct() {
        return totalProduct;
    }

    public void setTotalProduct(long totalProduct) {
        this.totalProduct = totalProduct;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }
}
