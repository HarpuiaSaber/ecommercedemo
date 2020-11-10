package com.toan.ecommercedemo.daos;

import com.toan.ecommercedemo.entities.Shop;
import com.toan.ecommercedemo.model.search.ShopSearch;

import java.util.List;

public interface ShopDao extends BaseDao<Shop, Long> {
    public Shop getByName(String name);

    public Long getTotalComment(Long id);

    public Long getTotalProduct(Long id, Integer status);

    public Long getTotalRating(Long id);

    public Shop getOfSeller(Long sellerId);

    public List<Shop> getAllPaging(ShopSearch search);

    public Long getTotalRecord(ShopSearch search);
}
