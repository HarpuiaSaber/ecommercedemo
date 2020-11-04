package com.toan.ecommercedemo.daos;

import com.toan.ecommercedemo.entities.Order;
import com.toan.ecommercedemo.model.search.OrderSearch;

import java.util.List;

public interface OrderDao extends BaseDao<Order, Long> {

    public List<Order> searchWithPaging(OrderSearch search);

    public Long getTotalRecord(OrderSearch search);

    public Long getTotalOrderOfShop(Long shopId, Integer status);
}
