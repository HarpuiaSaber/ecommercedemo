package com.toan.ecommercedemo.model.dto;

import java.io.Serializable;
import java.util.List;

public class ResponseDto<T> implements Serializable {

    private static final long serialVersionUID = 1L;
    private long recordsTotal;
    private long recordsFiltered;
    private List<T> data;

    public ResponseDto(long recordsTotal, long recordsFiltered, List<T> data) {
        this.recordsTotal = recordsTotal;
        this.recordsFiltered = recordsFiltered;
        this.data = data;
    }

    public ResponseDto(long recordsTotal, List<T> data) {
        this.recordsTotal = recordsTotal;
        this.data = data;
    }

    public long getRecordsTotal() {
        return recordsTotal;
    }

    public void setRecordsTotal(long recordsTotal) {
        this.recordsTotal = recordsTotal;
    }

    public long getRecordsFiltered() {
        return recordsFiltered;
    }

    public void setRecordsFiltered(long recordsFiltered) {
        this.recordsFiltered = recordsFiltered;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }
}
