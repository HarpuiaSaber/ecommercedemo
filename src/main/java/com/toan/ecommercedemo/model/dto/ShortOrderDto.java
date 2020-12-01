package com.toan.ecommercedemo.model.dto;

import java.io.Serializable;
import java.util.List;

public class ShortOrderDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String createdDate;
    private List<String> items;
    private Double totalMoney;
    private int status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public List<String> getItems() {
        return items;
    }

    public void setItems(List<String> items) {
        this.items = items;
    }

    public Double getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(Double totalMoney) {
        this.totalMoney = totalMoney;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
