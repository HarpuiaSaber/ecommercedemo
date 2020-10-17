package com.toan.ecommercedemo.model.dto;

import java.io.Serializable;
import java.util.List;

public class ShortProductDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private long id;

    private String name;

    private double unitPrice;

    private double unitPriceUSD;

    private double rating;

    private long totalComment;

    private List<String> images;

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

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public long getTotalComment() {
        return totalComment;
    }

    public void setTotalComment(long totalComment) {
        this.totalComment = totalComment;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }
}
