package com.toan.ecommercedemo.model.dto;

import java.io.Serializable;

public class ItemStatisticDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long totalSoldProduct;
    private Double totalIncome;

    public Long getTotalSoldProduct() {
        return totalSoldProduct;
    }

    public void setTotalSoldProduct(Long totalSoldProduct) {
        this.totalSoldProduct = totalSoldProduct;
    }

    public Double getTotalIncome() {
        return totalIncome;
    }

    public void setTotalIncome(Double totalIncome) {
        this.totalIncome = totalIncome;
    }
}
