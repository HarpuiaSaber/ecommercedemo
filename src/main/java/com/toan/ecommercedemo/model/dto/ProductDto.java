package com.toan.ecommercedemo.model.dto;

import java.io.Serializable;

public class ProductDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private long id;

    private String name;

    private double unitPrice;

    private double unitPriceUSD;

    private String updatedDate;

    private String image;

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

    public String getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(String updatedDate) {
        this.updatedDate = updatedDate;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
