package com.toan.ecommercedemo.daos.impls;

import com.toan.ecommercedemo.daos.ProductDao;
import com.toan.ecommercedemo.entities.Product;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public class ProductDaoImpl extends BaseDaoImpl<Product, Long> implements ProductDao {
}
