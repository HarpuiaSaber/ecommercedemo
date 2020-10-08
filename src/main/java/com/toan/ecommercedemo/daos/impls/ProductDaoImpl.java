package com.toan.ecommercedemo.daos.impls;

import com.toan.ecommercedemo.daos.ProductDao;
import com.toan.ecommercedemo.entities.Product;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class ProductDaoImpl extends BaseDaoImpl<Product, Long> implements ProductDao {
    @Override
    public List<Long> getAllId() {
        String hql = "SELECT p.id FROM Product p";
        Query query = entityManager.createQuery(hql);
        return query.getResultList();
    }
}
