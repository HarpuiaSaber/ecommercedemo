package com.toan.ecommercedemo.daos.impls;

import com.toan.ecommercedemo.daos.ProductDao;
import com.toan.ecommercedemo.entities.Brand;
import com.toan.ecommercedemo.entities.Category;
import com.toan.ecommercedemo.entities.Product;
import com.toan.ecommercedemo.model.search.ProductSearch;
import com.toan.ecommercedemo.utils.DateTimeUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import javax.transaction.Transactional;
import java.util.ArrayList;
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
    public List<Product> searchWithPaging(ProductSearch search) {
        // create returns the data type of critetia query
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Product> criteriaQuery = criteriaBuilder.createQuery(Product.class);

        // from and join entity
        Root<Product> root = criteriaQuery.from(Product.class);
        Join<Product, Category> category = root.join("category");
        Join<Product, Brand> brand = root.join("brand");

        // add predicate
        List<Predicate> predicates = new ArrayList<Predicate>();
        if (search.getCategoryId() != null) {
            Predicate predicate = criteriaBuilder.equal(category.get("id"), search.getCategoryId());
            predicates.add(predicate);
        }
        if (search.getCategoryId() != null) {
            Predicate predicate = criteriaBuilder.equal(brand.get("id"), search.getBrandId());
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
                Predicate predicate = criteriaBuilder.greaterThanOrEqualTo(root.get("createdDate"),
                        DateTimeUtils.parseDate(search.getFromDate(), DateTimeUtils.DD_MM_YYYY));
                predicates.add(predicate);
            } catch (RuntimeException ignored) {
            }
        }
        if (StringUtils.isNotBlank(search.getToDate())) {
            try {
                Predicate predicate = criteriaBuilder.lessThanOrEqualTo(root.get("createdDate"),
                        DateTimeUtils.parseDate(search.getToDate(), DateTimeUtils.DD_MM_YYYY));
                predicates.add(predicate);
            } catch (RuntimeException ignored) {
            }
        }
        criteriaQuery.where(predicates.toArray(new Predicate[]{}));

        // order
        criteriaQuery.orderBy(criteriaBuilder.desc(root.get("createdDate")));

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

        // add predicate
        List<Predicate> predicates = new ArrayList<Predicate>();
        if (search.getCategoryId() != null) {
            Predicate predicate = criteriaBuilder.equal(category.get("id"), search.getCategoryId());
            predicates.add(predicate);
        }
        if (search.getCategoryId() != null) {
            Predicate predicate = criteriaBuilder.equal(brand.get("id"), search.getBrandId());
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
                Predicate predicate = criteriaBuilder.greaterThanOrEqualTo(root.get("createdDate"),
                        DateTimeUtils.parseDate(search.getFromDate(), DateTimeUtils.DD_MM_YYYY));
                predicates.add(predicate);
            } catch (RuntimeException ignored) {
            }
        }
        if (StringUtils.isNotBlank(search.getToDate())) {
            try {
                Predicate predicate = criteriaBuilder.lessThanOrEqualTo(root.get("createdDate"),
                        DateTimeUtils.parseDate(search.getToDate(), DateTimeUtils.DD_MM_YYYY));
                predicates.add(predicate);
            } catch (RuntimeException ignored) {
            }
        }
        criteriaQuery.where(predicates.toArray(new Predicate[]{}));

        TypedQuery<Long> typedQuery = entityManager.createQuery(criteriaQuery.select(criteriaBuilder.count(root)));
        return typedQuery.getSingleResult();
    }
}
