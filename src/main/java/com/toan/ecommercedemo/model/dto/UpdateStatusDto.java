package com.toan.ecommercedemo.model.dto;

import java.io.Serializable;
import java.util.List;

public class UpdateStatusDto implements Serializable {
    private static final long serialVersionUID = 1L;

    private List<Long> ids;
    private Integer status;
    private String content;

    public List<Long> getIds() {
        return ids;
    }

    public void setIds(List<Long> ids) {
        this.ids = ids;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
