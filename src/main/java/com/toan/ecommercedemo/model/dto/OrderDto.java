package com.toan.ecommercedemo.model.dto;

import com.toan.ecommercedemo.enums.OrderStatus;

import java.io.Serializable;

public class OrderDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private Long contactId;
    private Long customerId;
    private Long paymentId;
    private OrderStatus status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getContactId() {
        return contactId;
    }

    public void setContactId(Long contactId) {
        this.contactId = contactId;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Long getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(Long paymentId) {
        this.paymentId = paymentId;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }
}
