package com.toan.ecommercedemo.model.search;

public class ProductSearch extends BaseDateSearch {

    private static final long serialVersionUID = 1L;

    private Long categoryId;

    private String name;

    private Double fromPrice;

    private Double toPrice;

    private Long brandId;

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getFromPrice() {
        return fromPrice;
    }

    public void setFromPrice(Double fromPrice) {
        this.fromPrice = fromPrice;
    }

    public Double getToPrice() {
        return toPrice;
    }

    public void setToPrice(Double toPrice) {
        this.toPrice = toPrice;
    }

    public Long getBrandId() {
        return brandId;
    }

    public void setBrandId(Long brandId) {
        this.brandId = brandId;
    }
}
