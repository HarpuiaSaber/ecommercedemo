package com.toan.ecommercedemo.daos;

import com.toan.ecommercedemo.entities.Brand;

public interface BrandDao extends BaseDao<Brand, Long> {

    public Brand getByName(String name);
}
