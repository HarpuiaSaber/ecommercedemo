package com.toan.ecommercedemo.model.dto;

import java.io.Serializable;
import java.util.List;

public class TikiProductDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private long id;
    private String name;
    private TikiBrandDto brand;
    private TikiSellerDto current_seller;
    private List<TikiProductImageDto> images;
    private double price;
    private double price_usd;
    private List<TikiSpecificationDto> specifications;
    private List<String> top_features;

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

    public TikiBrandDto getBrand() {
        return brand;
    }

    public void setBrand(TikiBrandDto brand) {
        this.brand = brand;
    }

    public TikiSellerDto getCurrent_seller() {
        return current_seller;
    }

    public void setCurrent_seller(TikiSellerDto current_seller) {
        this.current_seller = current_seller;
    }

    public List<TikiProductImageDto> getImages() {
        return images;
    }

    public void setImages(List<TikiProductImageDto> images) {
        this.images = images;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getPrice_usd() {
        return price_usd;
    }

    public void setPrice_usd(double price_usd) {
        this.price_usd = price_usd;
    }

    public List<TikiSpecificationDto> getSpecifications() {
        return specifications;
    }

    public void setSpecifications(List<TikiSpecificationDto> specifications) {
        this.specifications = specifications;
    }

    public List<String> getTop_features() {
        return top_features;
    }

    public void setTop_features(List<String> top_features) {
        this.top_features = top_features;
    }
}
