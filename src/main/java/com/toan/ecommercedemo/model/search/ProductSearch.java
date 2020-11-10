package com.toan.ecommercedemo.model.search;

import java.util.List;

public class ProductSearch extends BaseDateSearch {

    private static final long serialVersionUID = 1L;

    private Long categoryId;

    private List<Long> categoryIds;

    private String name;

    private Double fromPrice;

    private Double toPrice;

    private Long brandId;

    private Long shopId;

    private Integer status;

    private Integer shortType;

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public List<Long> getCategoryIds() {
        return categoryIds;
    }

    public void setCategoryIds(List<Long> categoryIds) {
        this.categoryIds = categoryIds;
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

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getShortType() {
        return shortType;
    }

    public void setShortType(Integer shortType) {
        this.shortType = shortType;
    }
}
