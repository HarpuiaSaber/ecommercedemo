package com.toan.ecommercedemo.model.dto;

import java.io.Serializable;
import java.util.List;

public class ShortProductDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private long id;

    private String name;

    private double unitPrice;

    private double unitPriceUSD;

    private int rating;

    private int totalComment;

    private List<ProductImageDto> images;

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

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public double getUnitPriceUSD() {
        return unitPriceUSD;
    }

    public void setUnitPriceUSD(double unitPriceUSD) {
        this.unitPriceUSD = unitPriceUSD;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public int getTotalComment() {
        return totalComment;
    }

    public void setTotalComment(int totalComment) {
        this.totalComment = totalComment;
    }

    public List<ProductImageDto> getImages() {
        return images;
    }

    public void setImages(List<ProductImageDto> images) {
        this.images = images;
    }
}
