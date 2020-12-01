package com.toan.ecommercedemo.model.dto;

import com.toan.ecommercedemo.enums.PaymentMethod;

import java.io.Serializable;

public class PaymentDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String name;
    private PaymentMethod method;

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

    public PaymentMethod getMethod() {
        return method;
    }

    public void setMethod(PaymentMethod method) {
        this.method = method;
    }
}
