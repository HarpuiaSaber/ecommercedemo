package com.toan.ecommercedemo.model.dto;

import java.io.Serializable;
import java.util.List;

public class TikiCommentDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private List<TikiCommentDataDto> data;

    public List<TikiCommentDataDto> getData() {
        return data;
    }

    public void setData(List<TikiCommentDataDto> data) {
        this.data = data;
    }
}
