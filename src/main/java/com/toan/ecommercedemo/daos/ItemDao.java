package com.toan.ecommercedemo.daos;

import com.toan.ecommercedemo.entities.Item;

import java.util.List;

public interface ItemDao extends BaseDao<Item, Long> {

    public List<Item> getSoldItemOfShop(Long shopId);
}
