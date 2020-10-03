package com.toan.ecommercedemo.daos.impls;

import com.toan.ecommercedemo.daos.ProductImageDao;
import com.toan.ecommercedemo.entities.ProductImage;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public class ProductImageDaoImpl extends BaseDaoImpl<ProductImage, Long> implements ProductImageDao {
}
