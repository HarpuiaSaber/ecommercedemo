package com.toan.ecommercedemo.model.dto;

import com.toan.ecommercedemo.entities.Brand;
import com.toan.ecommercedemo.entities.Category;
import com.toan.ecommercedemo.entities.Shop;

import java.io.Serializable;
import java.util.List;

public class ViewProductDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String name;

    private Double unitPrice;

    private Double unitPriceUSD;

    private Integer quantity;

    private String description;

    private Category category;

    private String brand;

    private ViewShopDto shop;

    private List<SpecificationDto> specifications;

    private List<String> images;

    private Double rating;

    private long totalComment;

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

    public Double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Double getUnitPriceUSD() {
        return unitPriceUSD;
    }

    public void setUnitPriceUSD(Double unitPriceUSD) {
        this.unitPriceUSD = unitPriceUSD;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public ViewShopDto getShop() {
        return shop;
    }

    public void setShop(ViewShopDto shop) {
        this.shop = shop;
    }

    public List<SpecificationDto> getSpecifications() {
        return specifications;
    }

    public void setSpecifications(List<SpecificationDto> specifications) {
        this.specifications = specifications;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public long getTotalComment() {
        return totalComment;
    }

    public void setTotalComment(long totalComment) {
        this.totalComment = totalComment;
    }
}
