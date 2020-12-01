package com.toan.ecommercedemo.model.dto;

import java.io.Serializable;
import java.util.List;

public class ViewOrderDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String customerName;
    private String customerPhone;
    private ViewContactDto contact;
    private List<ItemDto> items;
    private List<OrderHistoryDto> histories;
    private int status;
    private String paymentMethod;
    private String createdDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ViewContactDto getContact() {
        return contact;
    }

    public void setContact(ViewContactDto contact) {
        this.contact = contact;
    }

    public List<ItemDto> getItems() {
        return items;
    }

    public void setItems(List<ItemDto> items) {
        this.items = items;
    }

    public List<OrderHistoryDto> getHistories() {
        return histories;
    }

    public void setHistories(List<OrderHistoryDto> histories) {
        this.histories = histories;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }
}
