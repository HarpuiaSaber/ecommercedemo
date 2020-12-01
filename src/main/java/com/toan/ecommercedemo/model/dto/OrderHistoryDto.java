package com.toan.ecommercedemo.model.dto;

import java.io.Serializable;

public class OrderHistoryDto implements Serializable {

    private static final long serialVersionUID = 1L;
    private long id;
    private String createdDate;
    private int status;
    private String content;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
