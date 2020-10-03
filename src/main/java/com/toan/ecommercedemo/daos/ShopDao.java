package com.toan.ecommercedemo.daos;

import com.toan.ecommercedemo.entities.Shop;

public interface ShopDao extends BaseDao<Shop, Long> {
    public Shop getByName(String name);
}
