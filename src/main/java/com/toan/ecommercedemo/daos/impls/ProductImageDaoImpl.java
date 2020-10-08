package com.toan.ecommercedemo.daos.impls;

import com.toan.ecommercedemo.daos.ProductImageDao;
import com.toan.ecommercedemo.entities.Product;
import com.toan.ecommercedemo.entities.ProductImage;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Repository
@Transactional
public class ProductImageDaoImpl extends BaseDaoImpl<ProductImage, Long> implements ProductImageDao {
    @Override
    public List<ProductImage> getByProductId(Long productId) {
        // create returns the data type of critetia query
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<ProductImage> criteriaQuery = criteriaBuilder.createQuery(ProductImage.class);

        // from and join entity
        Root<ProductImage> root = criteriaQuery.from(ProductImage.class);
        Join<ProductImage, Product> product = root.join("product");

        // add predicate
        List<Predicate> predicates = new ArrayList<>();
        if (productId != null) {
            Predicate predicate = criteriaBuilder.equal(product.get("id"), productId);
            predicates.add(predicate);
        }
        criteriaQuery.where(predicates.toArray(new Predicate[]{}));

        // create query
        TypedQuery<ProductImage> typedQuery = entityManager.createQuery(criteriaQuery.select(root));

        return typedQuery.getResultList();
    }
}
