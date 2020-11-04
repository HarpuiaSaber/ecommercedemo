package com.toan.ecommercedemo.daos;

import com.toan.ecommercedemo.entities.Brand;
import com.toan.ecommercedemo.model.search.ProductSearch;

import java.util.List;

public interface BrandDao extends BaseDao<Brand, Long> {

    public Brand getByName(String name);

    public List<Brand> getOfProduct(ProductSearch search);
}
