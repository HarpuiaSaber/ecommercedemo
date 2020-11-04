package com.toan.ecommercedemo.daos.impls;

import com.toan.ecommercedemo.daos.ShopDao;
import com.toan.ecommercedemo.entities.Comment;
import com.toan.ecommercedemo.entities.Product;
import com.toan.ecommercedemo.entities.Shop;
import com.toan.ecommercedemo.entities.User;
import com.toan.ecommercedemo.enums.ProductStatus;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Repository
@Transactional
public class ShopDaoImpl extends BaseDaoImpl<Shop, Long> implements ShopDao {
    @Override
    public Shop getByName(String name) {
        // create returns the data type of critetia query
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Shop> criteriaQuery = criteriaBuilder.createQuery(Shop.class);

        // from and join entity
        Root<Shop> root = criteriaQuery.from(Shop.class);

        // add predicate
        List<Predicate> predicates = new ArrayList<>();
        if (StringUtils.isNotBlank(name)) {
            Predicate predicate = criteriaBuilder.equal(root.get("name"), name);
            predicates.add(predicate);
        }
        criteriaQuery.where(predicates.toArray(new Predicate[]{}));

        // create query
        TypedQuery<Shop> typedQuery = entityManager.createQuery(criteriaQuery.select(root));
        try {
            return typedQuery.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public Long getTotalComment(Long id) {
        // create returns the data type of critetia query
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);

        // from and join entity
        Root<Comment> root = criteriaQuery.from(Comment.class);
        Join<Comment, Product> product = root.join("product");
        Join<Product, Shop> shop = product.join("shop");

        // add predicate
        List<Predicate> predicates = new ArrayList<>();
        if (id != null) {
            Predicate predicate = criteriaBuilder.equal(shop.get("id"), id);
            predicates.add(predicate);
        }
        criteriaQuery.where(predicates.toArray(new Predicate[]{}));

        // create query
        TypedQuery<Long> typedQuery = entityManager.createQuery(criteriaQuery.select(criteriaBuilder.count(root)));
        return typedQuery.getSingleResult();
    }

    @Override
    public Long getTotalProduct(Long id, Integer status) {
        // create returns the data type of critetia query
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);

        // from and join entity
        Root<Product> root = criteriaQuery.from(Product.class);
        Join<Product, Shop> shop = root.join("shop");

        // add predicate
        List<Predicate> predicates = new ArrayList<>();
        if (id != null) {
            Predicate predicate = criteriaBuilder.equal(shop.get("id"), id);
            predicates.add(predicate);
        }
        if (status != null) {
            Predicate predicate = criteriaBuilder.equal(root.get("status"), ProductStatus.valueOf(status));
            predicates.add(predicate);
        }
        criteriaQuery.where(predicates.toArray(new Predicate[]{}));

        // create query
        TypedQuery<Long> typedQuery = entityManager.createQuery(criteriaQuery.select(criteriaBuilder.count(root)));
        return typedQuery.getSingleResult();
    }

    @Override
    public Long getTotalRating(Long id) {
        // create returns the data type of critetia query
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);

        // from and join entity
        Root<Comment> root = criteriaQuery.from(Comment.class);
        Join<Comment, Product> product = root.join("product");
        Join<Product, Shop> shop = product.join("shop");

        // add predicate
        List<Predicate> predicates = new ArrayList<>();
        if (id != null) {
            Predicate predicate = criteriaBuilder.equal(shop.get("id"), id);
            predicates.add(predicate);
        }
        criteriaQuery.where(predicates.toArray(new Predicate[]{}));

        // create query
        TypedQuery<Long> typedQuery = entityManager.createQuery(criteriaQuery.select(criteriaBuilder.sum(root.get("rating")).as(Long.class)));
        try {
            return typedQuery.getSingleResult();
        } catch (NoResultException e) {
            return 0L;
        }
    }

    @Override
    public Shop getOfSeller(Long sellerId) {
        // create returns the data type of critetia query
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Shop> criteriaQuery = criteriaBuilder.createQuery(Shop.class);

        // from and join entity
        Root<Shop> root = criteriaQuery.from(Shop.class);
        Join<Shop, User> seller = root.join("seller");

        // add predicate
        List<Predicate> predicates = new ArrayList<>();
        if (sellerId != null) {
            Predicate predicate = criteriaBuilder.equal(seller.get("id"), sellerId);
            predicates.add(predicate);
        }
        criteriaQuery.where(predicates.toArray(new Predicate[]{}));

        // create query
        TypedQuery<Shop> typedQuery = entityManager.createQuery(criteriaQuery.select(root));
        try {
            return typedQuery.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
}
