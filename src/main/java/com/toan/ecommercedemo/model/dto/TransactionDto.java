package com.toan.ecommercedemo.model.dto;

import com.toan.ecommercedemo.enums.OrderStatus;

import java.io.Serializable;

public class TransactionDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private Long orderId;
    private String paymentId;
    private Double paymentMoney;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    public Double getPaymentMoney() {
        return paymentMoney;
    }

    public void setPaymentMoney(Double paymentMoney) {
        this.paymentMoney = paymentMoney;
    }
}
