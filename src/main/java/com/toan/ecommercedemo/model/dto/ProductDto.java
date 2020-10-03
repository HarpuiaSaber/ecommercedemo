package com.toan.ecommercedemo.model.dto;

import com.toan.ecommercedemo.entities.Brand;
import com.toan.ecommercedemo.entities.Category;
import com.toan.ecommercedemo.entities.Shop;
import com.toan.ecommercedemo.entities.Specification;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

public class ProductDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String name;

    private Double unitPrice;

    private Double unitPriceUSD;

    private Integer quantity;

    private String description;

    //private Category category;

    //private Brand brand;

    //private Shop shop;

    //private List<Specification> specifications;


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
}
