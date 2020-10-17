package com.toan.ecommercedemo.daos;

import com.toan.ecommercedemo.entities.Product;
import com.toan.ecommercedemo.model.search.ProductSearch;

import java.util.List;

public interface ProductDao extends BaseDao<Product, Long> {

    public List<Long> getAllId();

    public List<Product> searchAll(ProductSearch search);

    public List<Product> searchWithPaging(ProductSearch search);

    public Long totalRecord(ProductSearch search);
}
