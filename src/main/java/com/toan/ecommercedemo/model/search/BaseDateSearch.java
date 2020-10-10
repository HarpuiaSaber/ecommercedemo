package com.toan.ecommercedemo.model.search;

import java.io.Serializable;

public class BaseDateSearch extends BaseSearch {

    private static final long serialVersionUID = 1L;

    private String toDate;
    private String fromDate;

    public String getToDate() {
        return toDate;
    }

    public void setToDate(String toDate) {
        this.toDate = toDate;
    }

    public String getFromDate() {
        return fromDate;
    }

    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }
}
