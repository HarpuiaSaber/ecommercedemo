package com.toan.ecommercedemo.model.dto;

import java.io.Serializable;

public class ItemDto implements Serializable {

    private static final long serialVersionUID = 1L;
    private Long id;
    private Double unitPrice;
    private Double unitPriceUSD;
    private Integer quantity;
    private CartProductDto product;
    private Long orderId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public CartProductDto getProduct() {
        return product;
    }

    public void setProduct(CartProductDto product) {
        this.product = product;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }
}
