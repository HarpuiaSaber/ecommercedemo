package com.toan.ecommercedemo.services;

import com.toan.ecommercedemo.model.dto.OrderDto;
import com.toan.ecommercedemo.model.dto.TrackingOrderDto;
import com.toan.ecommercedemo.model.search.OrderSearch;

import java.util.List;

public interface OrderService {
    public void add(OrderDto dto);

    public List<TrackingOrderDto> searchWithPaging(OrderSearch search);

    public Long getTotalRecord(OrderSearch search);

    public Long getTotalOrderOfShop(Long shopId, Integer status);

    public void updateStatus(Long orderId, Integer status);
}
