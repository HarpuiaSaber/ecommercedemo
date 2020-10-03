package com.toan.ecommercedemo.model.search;

import java.io.Serializable;

public class BaseSearch implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer start;
    private Integer length;

    public Integer getStart() {
        return start;
    }

    public void setStart(Integer start) {
        this.start = start;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }
}
