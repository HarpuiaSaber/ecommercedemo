package com.toan.ecommercedemo.daos.impls;

import com.toan.ecommercedemo.daos.ProductDao;
import com.toan.ecommercedemo.entities.*;
import com.toan.ecommercedemo.enums.ProductStatus;
import com.toan.ecommercedemo.model.search.ProductSearch;
import com.toan.ecommercedemo.utils.DateTimeUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
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

    @Override
    public List<Product> searchAll(ProductSearch search) {
        // create returns the data type of critetia query
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Product> criteriaQuery = criteriaBuilder.createQuery(Product.class);

        // from and join entity
        Root<Product> root = criteriaQuery.from(Product.class);
        Join<Product, Category> category = root.join("category");
        Join<Product, Brand> brand = root.join("brand");
        Join<Product, Shop> shop = root.join("shop");

        // add predicate
        List<Predicate> predicates = new ArrayList<Predicate>();
        List<Long> categoryIds = search.getCategoryIds();
        if (categoryIds != null && categoryIds.size() > 0) {
            Predicate predicate = category.get("id").in(categoryIds);
            predicates.add(predicate);
        }
        if (search.getBrandId() != null) {
            Predicate predicate = criteriaBuilder.equal(brand.get("id"), search.getBrandId());
            predicates.add(predicate);
        }
        if (search.getShopId() != null) {
            Predicate predicate = criteriaBuilder.equal(shop.get("id"), search.getShopId());
            predicates.add(predicate);
        }
        if (StringUtils.isNotBlank(search.getName())) {
            Predicate predicate = criteriaBuilder.like(root.get("name"), "%" + search.getName() + "%");
            predicates.add(predicate);
        }
        if (search.getFromPrice() != null) {
            Predicate predicate = criteriaBuilder.greaterThanOrEqualTo(root.get("unitPrice"),
                    search.getFromPrice());
            predicates.add(predicate);
        }
        if (search.getToPrice() != null) {
            Predicate predicate = criteriaBuilder.lessThanOrEqualTo(root.get("unitPrice"),
                    search.getToPrice());
            predicates.add(predicate);
        }
        if (StringUtils.isNotBlank(search.getFromDate())) {
            try {
                Predicate predicate = criteriaBuilder.greaterThanOrEqualTo(root.get("updatedDate"),
                        DateTimeUtils.parseDate(search.getFromDate(), DateTimeUtils.DD_MM_YYYY));
                predicates.add(predicate);
            } catch (RuntimeException ignored) {
            }
        }
        if (StringUtils.isNotBlank(search.getToDate())) {
            try {
                Predicate predicate = criteriaBuilder.lessThanOrEqualTo(root.get("updatedDate"),
                        DateTimeUtils.parseDate(search.getToDate(), DateTimeUtils.DD_MM_YYYY));
                predicates.add(predicate);
            } catch (RuntimeException ignored) {
            }
        }
        if (search.getStatus() != null) {
            Predicate predicate = criteriaBuilder.equal(root.get("status"), ProductStatus.valueOf(search.getStatus()));
            predicates.add(predicate);
        }
        criteriaQuery.where(predicates.toArray(new Predicate[]{}));

        // create query
        TypedQuery<Product> typedQuery = entityManager.createQuery(criteriaQuery.select(root));
        return typedQuery.getResultList();
    }

    @Override
    public List<Product> searchWithPaging(ProductSearch search) {
        // create returns the data type of critetia query
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Product> criteriaQuery = criteriaBuilder.createQuery(Product.class);

        // from and join entity
        Root<Product> root = criteriaQuery.from(Product.class);
        Join<Product, Category> category = root.join("category");
        Join<Product, Brand> brand = root.join("brand");
        Join<Product, Shop> shop = root.join("shop");

        // add predicate
        List<Predicate> predicates = new ArrayList<Predicate>();
        List<Long> categoryIds = search.getCategoryIds();
        if (categoryIds != null && categoryIds.size() > 0) {
            Predicate predicate = category.get("id").in(categoryIds);
            predicates.add(predicate);
        }
        if (search.getBrandId() != null) {
            Predicate predicate = criteriaBuilder.equal(brand.get("id"), search.getBrandId());
            predicates.add(predicate);
        }
        if (search.getShopId() != null) {
            Predicate predicate = criteriaBuilder.equal(shop.get("id"), search.getShopId());
            predicates.add(predicate);
        }
        if (StringUtils.isNotBlank(search.getName())) {
            Predicate predicate = criteriaBuilder.like(root.get("name"), "%" + search.getName() + "%");
            predicates.add(predicate);
        }
        if (search.getFromPrice() != null) {
            Predicate predicate = criteriaBuilder.greaterThanOrEqualTo(root.get("unitPrice"),
                    search.getFromPrice());
            predicates.add(predicate);
        }
        if (search.getToPrice() != null) {
            Predicate predicate = criteriaBuilder.lessThanOrEqualTo(root.get("unitPrice"),
                    search.getToPrice());
            predicates.add(predicate);
        }
        if (StringUtils.isNotBlank(search.getFromDate())) {
            try {
                Predicate predicate = criteriaBuilder.greaterThanOrEqualTo(root.get("updatedDate"),
                        DateTimeUtils.parseDate(search.getFromDate(), DateTimeUtils.DD_MM_YYYY));
                predicates.add(predicate);
            } catch (RuntimeException ignored) {
            }
        }
        if (StringUtils.isNotBlank(search.getToDate())) {
            try {
                Predicate predicate = criteriaBuilder.lessThanOrEqualTo(root.get("updatedDate"),
                        DateTimeUtils.parseDate(search.getToDate(), DateTimeUtils.DD_MM_YYYY));
                predicates.add(predicate);
            } catch (RuntimeException ignored) {
            }
        }
        if (search.getStatus() != null) {
            Predicate predicate = criteriaBuilder.equal(root.get("status"), ProductStatus.valueOf(search.getStatus()));
            predicates.add(predicate);
        }
        criteriaQuery.where(predicates.toArray(new Predicate[]{}));

        // order
        Integer order = search.getSortType();
        if (order != null) {
            if (order == 0) {
                criteriaQuery.orderBy(criteriaBuilder.desc(root.get("updatedDate")));
            }
            if (order == 1) {
                criteriaQuery.orderBy(criteriaBuilder.desc(root.get("updatedDate")));
            }
            if (order == 2) {
                criteriaQuery.orderBy(criteriaBuilder.desc(root.get("unitPrice")));
            }
            if (order == 3) {
                criteriaQuery.orderBy(criteriaBuilder.asc(root.get("unitPrice")));
            }
        } else {
            criteriaQuery.orderBy(criteriaBuilder.desc(criteriaBuilder.size(root.<Collection>get("comments"))), criteriaBuilder.desc(root.get("createdDate")));
            //criteriaQuery.orderBy(criteriaBuilder.desc(criteriaBuilder.size(root.<Collection>get("comments"))));
        }
        // paging
        // create query
        TypedQuery<Product> typedQuery = entityManager.createQuery(criteriaQuery.select(root));
        if (search.getStart() != null) {
            typedQuery.setFirstResult((search.getStart()));
        }

        typedQuery.setMaxResults(search.getLength());
        return typedQuery.getResultList();
    }

    @Override
    public Long totalRecord(ProductSearch search) {
        // create returns the data type of critetia query
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);

        // from and join entity
        Root<Product> root = criteriaQuery.from(Product.class);
        Join<Product, Category> category = root.join("category");
        Join<Product, Brand> brand = root.join("brand");
        Join<Product, Shop> shop = root.join("shop");

        // add predicate
        List<Predicate> predicates = new ArrayList<Predicate>();
        List<Long> categoryIds = search.getCategoryIds();
        if (categoryIds != null && categoryIds.size() > 0) {
            Predicate predicate = category.get("id").in(categoryIds);
            predicates.add(predicate);
        }
        if (search.getBrandId() != null) {
            Predicate predicate = criteriaBuilder.equal(brand.get("id"), search.getBrandId());
            predicates.add(predicate);
        }
        if (search.getShopId() != null) {
            Predicate predicate = criteriaBuilder.equal(shop.get("id"), search.getShopId());
            predicates.add(predicate);
        }
        if (StringUtils.isNotBlank(search.getName())) {
            Predicate predicate = criteriaBuilder.like(root.get("name"), "%" + search.getName() + "%");
            predicates.add(predicate);
        }
        if (search.getFromPrice() != null) {
            Predicate predicate = criteriaBuilder.greaterThanOrEqualTo(root.get("unitPrice"),
                    search.getFromPrice());
            predicates.add(predicate);
        }
        if (search.getToPrice() != null) {
            Predicate predicate = criteriaBuilder.lessThanOrEqualTo(root.get("unitPrice"),
                    search.getToPrice());
            predicates.add(predicate);
        }
        if (StringUtils.isNotBlank(search.getFromDate())) {
            try {
                Predicate predicate = criteriaBuilder.greaterThanOrEqualTo(root.get("updatedDate"),
                        DateTimeUtils.parseDate(search.getFromDate(), DateTimeUtils.DD_MM_YYYY));
                predicates.add(predicate);
            } catch (RuntimeException ignored) {
            }
        }
        if (StringUtils.isNotBlank(search.getToDate())) {
            try {
                Predicate predicate = criteriaBuilder.lessThanOrEqualTo(root.get("updatedDate"),
                        DateTimeUtils.parseDate(search.getToDate(), DateTimeUtils.DD_MM_YYYY));
                predicates.add(predicate);
            } catch (RuntimeException ignored) {
            }
        }
        if (search.getStatus() != null) {
            Predicate predicate = criteriaBuilder.equal(root.get("status"), ProductStatus.valueOf(search.getStatus()));
            predicates.add(predicate);
        }
        criteriaQuery.where(predicates.toArray(new Predicate[]{}));

        TypedQuery<Long> typedQuery = entityManager.createQuery(criteriaQuery.select(criteriaBuilder.count(root)));
        return typedQuery.getSingleResult();
    }

    @Override
    public List<Product> getCustomerProductWithoutComment(Long customerId, Integer start, Integer length) {
        String hql = "SELECT p FROM Item i " +
                "JOIN i.order o " +
                "JOIN o.customer c " +
                "JOIN i.product p " +
                "WHERE c.id = :customerId " +
                "AND o.status = 3" +
                "AND p.id NOT IN " +
                "(SELECT c.product.id FROM Comment c " +
                "WHERE c.customer.id = :customerId) " +
                "GROUP BY p.id";
        Query query = entityManager.createQuery(hql);
        query.setParameter("customerId", customerId);
        query.setFirstResult(start);
        query.setMaxResults(length);
        return query.getResultList();
    }

    @Override
    public Long countCustomerProductWithoutComment(Long customerId) {
        String hql = "SELECT COUNT(p) FROM Item i " +
                "JOIN i.order o " +
                "JOIN o.customer c " +
                "JOIN i.product p " +
                "WHERE c.id = :customerId " +
                "AND o.status = 3" +
                "AND p.id NOT IN " +
                "(SELECT c.product.id FROM Comment c " +
                "WHERE c.customer.id = :customerId) " +
                "GROUP BY p.id";
        Query query = entityManager.createQuery(hql);
        query.setParameter("customerId", customerId);
        try {
            return (Long) query.getSingleResult();
        } catch (NoResultException e) {
            return 0L;
        }

    }

    @Override
    public List<Product> getCustomerProductInComment(Long userId) {
        // create returns the data type of critetia query
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Product> criteriaQuery = criteriaBuilder.createQuery(Product.class);

        // from and join entity
        Root<Comment> root = criteriaQuery.from(Comment.class);
        Join<Comment, Product> product = root.join("product");
        Join<Comment, User> customer = root.join("customer");

        // add predicate
        List<Predicate> predicates = new ArrayList<Predicate>();
        if (userId != null) {
            Predicate predicate = criteriaBuilder.equal(customer.get("id"), userId);
            predicates.add(predicate);
        }
        criteriaQuery.where(predicates.toArray(new Predicate[]{}));

        // order
        //criteriaQuery.orderBy(criteriaBuilder.desc(criteriaBuilder.size(root.<Collection>get("comments"))), criteriaBuilder.desc(root.get("createdDate")));
        //criteriaQuery.orderBy(criteriaBuilder.desc(criteriaBuilder.size(root.<Collection>get("comments"))));

        // paging
        // create query
        TypedQuery<Product> typedQuery = entityManager.createQuery(criteriaQuery.select(product));
        return typedQuery.getResultList();
    }
}
