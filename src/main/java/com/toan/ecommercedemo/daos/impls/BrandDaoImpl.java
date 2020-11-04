package com.toan.ecommercedemo.daos.impls;

import com.toan.ecommercedemo.daos.BrandDao;
import com.toan.ecommercedemo.daos.CategoryDao;
import com.toan.ecommercedemo.entities.*;
import com.toan.ecommercedemo.model.search.ProductSearch;
import com.toan.ecommercedemo.utils.DateTimeUtils;
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
public class BrandDaoImpl extends BaseDaoImpl<Brand, Long> implements BrandDao {
    @Override
    public Brand getByName(String name) {
        // create returns the data type of critetia query
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Brand> criteriaQuery = criteriaBuilder.createQuery(Brand.class);

        // from and join entity
        Root<Brand> root = criteriaQuery.from(Brand.class);

        // add predicate
        List<Predicate> predicates = new ArrayList<>();
        if (StringUtils.isNotBlank(name)) {
            Predicate predicate = criteriaBuilder.equal(root.get("name"), name);
            predicates.add(predicate);
        }
        criteriaQuery.where(predicates.toArray(new Predicate[]{}));

        // create query
        TypedQuery<Brand> typedQuery = entityManager.createQuery(criteriaQuery.select(root));
        try {
            return typedQuery.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public List<Brand> getOfProduct(ProductSearch search) {
        // create returns the data type of critetia query
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Brand> criteriaQuery = criteriaBuilder.createQuery(Brand.class);

        // from and join entity
        Root<Product> root = criteriaQuery.from(Product.class);
        Join<Product, Brand> brand = root.join("brand");
        Join<Product, Category> category = root.join("category");
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
        criteriaQuery.groupBy(brand.get("id"));

        //order
        criteriaQuery.orderBy(criteriaBuilder.asc(brand.get("name")));

        // create query
        TypedQuery<Brand> typedQuery = entityManager.createQuery(criteriaQuery.select(brand));
        return typedQuery.getResultList();
    }
}
