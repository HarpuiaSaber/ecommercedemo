package com.toan.ecommercedemo.daos.impls;

import com.toan.ecommercedemo.daos.OrderDao;
import com.toan.ecommercedemo.entities.*;
import com.toan.ecommercedemo.entities.Order;
import com.toan.ecommercedemo.enums.OrderStatus;
import com.toan.ecommercedemo.model.search.OrderSearch;
import com.toan.ecommercedemo.utils.DateTimeUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Repository
@Transactional
public class OrderDaoImpl extends BaseDaoImpl<Order, Long> implements OrderDao {
    @Override
    public List<Order> searchWithPaging(OrderSearch search) {
        // create returns the data type of critetia query
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Order> criteriaQuery = criteriaBuilder.createQuery(Order.class);

        // from and join entity
        Root<Item> root = criteriaQuery.from(Item.class);
        Join<Item, Order> order = root.join("order");
        Join<Order, User> customer = order.join("customer");
        Join<Item, Product> product = root.join("product");
        Join<Product, Shop> shop = product.join("shop");
        Join<Shop, User> seller = shop.join("seller");

        // add predicate
        List<Predicate> predicates = new ArrayList<Predicate>();
        if (search.getCustomerId() != null) {
            Predicate predicate = criteriaBuilder.equal(customer.get("id"), search.getCustomerId());
            predicates.add(predicate);
        }
        if (search.getSellerId() != null) {
            Predicate predicate = criteriaBuilder.equal(seller.get("id"), search.getSellerId());
            predicates.add(predicate);
        }
        if (search.getStatus() != null) {
            Predicate predicate = criteriaBuilder.equal(order.get("status"), OrderStatus.valueOf(search.getStatus()));
            predicates.add(predicate);
        }
        if (StringUtils.isNotBlank(search.getFromDate())) {
            try {
                Predicate predicate = criteriaBuilder.greaterThanOrEqualTo(order.get("createdDate"),
                        DateTimeUtils.parseDate(search.getFromDate(), DateTimeUtils.DD_MM_YYYY));
                predicates.add(predicate);
            } catch (RuntimeException ignored) {
            }
        }
        if (StringUtils.isNotBlank(search.getToDate())) {
            try {
                Predicate predicate = criteriaBuilder.lessThanOrEqualTo(order.get("createdDate"),
                        DateTimeUtils.parseDate(search.getToDate(), DateTimeUtils.DD_MM_YYYY));
                predicates.add(predicate);
            } catch (RuntimeException ignored) {
            }
        }
        criteriaQuery.where(predicates.toArray(new Predicate[]{}));
        criteriaQuery.groupBy(order.get("id"));

        // order
        criteriaQuery.orderBy(criteriaBuilder.desc(order.get("createdDate")));
        //criteriaQuery.orderBy(criteriaBuilder.desc(criteriaBuilder.size(root.<Collection>get("comments"))));

        // paging
        // create query
        TypedQuery<Order> typedQuery = entityManager.createQuery(criteriaQuery.select(order));
        if (search.getStart() != null) {
            typedQuery.setFirstResult((search.getStart()));
        }
        typedQuery.setMaxResults(search.getLength());
        return typedQuery.getResultList();
    }

    @Override
    public Long getTotalRecord(OrderSearch search) {
        // create returns the data type of critetia query
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);

        // from and join entity
        Root<Item> root = criteriaQuery.from(Item.class);
        Join<Item, Order> order = root.join("order");
        Join<Order, User> customer = order.join("customer");
        Join<Item, Product> product = root.join("product");
        Join<Product, Shop> shop = product.join("shop");
        Join<Shop, User> seller = shop.join("seller");

        // add predicate
        List<Predicate> predicates = new ArrayList<Predicate>();
        if (search.getCustomerId() != null) {
            Predicate predicate = criteriaBuilder.equal(customer.get("id"), search.getCustomerId());
            predicates.add(predicate);
        }
        if (search.getSellerId() != null) {
            Predicate predicate = criteriaBuilder.equal(seller.get("id"), search.getSellerId());
            predicates.add(predicate);
        }
        if (search.getStatus() != null) {
            Predicate predicate = criteriaBuilder.equal(order.get("status"), OrderStatus.valueOf(search.getStatus()));
            predicates.add(predicate);
        }
        if (StringUtils.isNotBlank(search.getFromDate())) {
            try {
                Predicate predicate = criteriaBuilder.greaterThanOrEqualTo(order.get("createdDate"),
                        DateTimeUtils.parseDate(search.getFromDate(), DateTimeUtils.DD_MM_YYYY));
                predicates.add(predicate);
            } catch (RuntimeException ignored) {
            }
        }
        if (StringUtils.isNotBlank(search.getToDate())) {
            try {
                Predicate predicate = criteriaBuilder.lessThanOrEqualTo(order.get("createdDate"),
                        DateTimeUtils.parseDate(search.getToDate(), DateTimeUtils.DD_MM_YYYY));
                predicates.add(predicate);
            } catch (RuntimeException ignored) {
            }
        }
        criteriaQuery.where(predicates.toArray(new Predicate[]{}));
        criteriaQuery.groupBy(order.get("id"));

        // create query
        TypedQuery<Long> typedQuery = entityManager.createQuery(criteriaQuery.select(criteriaBuilder.count(order)));
        try {
            return new Long(typedQuery.getResultList().size());
        } catch (Exception e) {
            return 0L;
        }
    }

    @Override
    public Long getTotalOrderOfShop(Long shopId, Integer status) {
        // create returns the data type of critetia query
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);

        // from and join entity
        Root<Item> root = criteriaQuery.from(Item.class);
        Join<Item, Order> order = root.join("order");
        Join<Item, Product> product = root.join("product");
        Join<Product, Shop> shop = product.join("shop");

        // add predicate
        List<Predicate> predicates = new ArrayList<Predicate>();
        if (shopId != null) {
            Predicate predicate = criteriaBuilder.equal(shop.get("id"), shopId);
            predicates.add(predicate);
        }
        if (status != null) {
            Predicate predicate = criteriaBuilder.equal(order.get("status"), OrderStatus.valueOf(status));
            predicates.add(predicate);
        }
        criteriaQuery.where(predicates.toArray(new Predicate[]{}));
        criteriaQuery.groupBy(order.get("id"));

        // create query
        TypedQuery<Long> typedQuery = entityManager.createQuery(criteriaQuery.select(criteriaBuilder.count(order)));
        try {
            return typedQuery.getSingleResult();
        } catch (Exception e) {
            return 0L;
        }
    }
}
