package com.toan.ecommercedemo.daos;

import com.toan.ecommercedemo.entities.ProductImage;

import java.util.List;

public interface ProductImageDao extends BaseDao<ProductImage, Long> {
    public List<ProductImage> getByProductId(Long productId);
}
