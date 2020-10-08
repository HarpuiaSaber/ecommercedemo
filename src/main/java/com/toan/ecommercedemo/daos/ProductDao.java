package com.toan.ecommercedemo.daos;

import com.toan.ecommercedemo.entities.Product;

import java.util.List;

public interface ProductDao extends BaseDao<Product, Long> {
    public List<Long> getAllId();
}
