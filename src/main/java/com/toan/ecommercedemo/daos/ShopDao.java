package com.toan.ecommercedemo.daos;

import com.toan.ecommercedemo.entities.Shop;

public interface ShopDao extends BaseDao<Shop, Long> {
    public Shop getByName(String name);

    public Long getTotalComment(Long id);

    public Long getTotalProduct(Long id, Integer status);

    public Long getTotalRating(Long id);

    public Shop getOfSeller(Long sellerId);
}
