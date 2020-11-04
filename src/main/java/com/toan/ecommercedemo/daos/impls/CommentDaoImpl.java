package com.toan.ecommercedemo.daos.impls;

import com.toan.ecommercedemo.daos.CommentDao;
import com.toan.ecommercedemo.entities.Comment;
import com.toan.ecommercedemo.entities.Product;
import com.toan.ecommercedemo.entities.User;
import com.toan.ecommercedemo.model.search.CommentSearch;
import com.toan.ecommercedemo.utils.DateTimeUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Repository
@Transactional
public class CommentDaoImpl extends BaseDaoImpl<Comment, Long> implements CommentDao {

    @Override
    public List<Comment> search(CommentSearch search) {
        // create returns the data type of critetia query
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Comment> criteriaQuery = criteriaBuilder.createQuery(Comment.class);

        // from and join entity
        Root<Comment> root = criteriaQuery.from(Comment.class);
        Join<Comment, User> customer = root.join("customer");
        Join<Comment, Product> product = root.join("product");

        // add predicate
        List<Predicate> predicates = new ArrayList<Predicate>();
        if (search.getCustomerId() != null) {
            Predicate predicate = criteriaBuilder.equal(customer.get("id"), search.getCustomerId());
            predicates.add(predicate);
        }
        if (search.getProductId() != null) {
            Predicate predicate = criteriaBuilder.equal(product.get("id"), search.getProductId());
            predicates.add(predicate);
        }
        if (search.getRating() != null) {
            Predicate predicate = criteriaBuilder.equal(root.get("rating"), search.getRating());
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
        //criteriaQuery.orderBy(criteriaBuilder.desc(root.get("createdDate")));
        //criteriaQuery.orderBy(criteriaBuilder.desc(criteriaBuilder.size(root.<Collection>get("comments"))));

        // create query
        TypedQuery<Comment> typedQuery = entityManager.createQuery(criteriaQuery.select(root));
        return typedQuery.getResultList();
    }

    @Override
    public List<Comment> searchWithPaging(CommentSearch search) {
        // create returns the data type of critetia query
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Comment> criteriaQuery = criteriaBuilder.createQuery(Comment.class);

        // from and join entity
        Root<Comment> root = criteriaQuery.from(Comment.class);
        Join<Comment, User> customer = root.join("customer");
        Join<Comment, Product> product = root.join("product");

        // add predicate
        List<Predicate> predicates = new ArrayList<Predicate>();
        if (search.getCustomerId() != null) {
            Predicate predicate = criteriaBuilder.equal(customer.get("id"), search.getCustomerId());
            predicates.add(predicate);
        }
        if (search.getProductId() != null) {
            Predicate predicate = criteriaBuilder.equal(product.get("id"), search.getProductId());
            predicates.add(predicate);
        }
        if (search.getRating() != null) {
            Predicate predicate = criteriaBuilder.equal(root.get("rating"), search.getRating());
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
        //criteriaQuery.orderBy(criteriaBuilder.desc(root.get("createdDate")));
        //criteriaQuery.orderBy(criteriaBuilder.desc(criteriaBuilder.size(root.<Collection>get("comments"))));

        // paging
        // create query
        TypedQuery<Comment> typedQuery = entityManager.createQuery(criteriaQuery.select(root));
        if (search.getStart() != null) {
            typedQuery.setFirstResult((search.getStart()));
        }

        typedQuery.setMaxResults(search.getLength());
        return typedQuery.getResultList();
    }

    @Override
    public Long getTotalRecord(CommentSearch search) {
        // create returns the data type of critetia query
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);

        // from and join entity
        Root<Comment> root = criteriaQuery.from(Comment.class);
        Join<Comment, User> customer = root.join("customer");
        Join<Comment, Product> product = root.join("product");

        // add predicate
        List<Predicate> predicates = new ArrayList<Predicate>();
        if (search.getCustomerId() != null) {
            Predicate predicate = criteriaBuilder.equal(customer.get("id"), search.getCustomerId());
            predicates.add(predicate);
        }
        if (search.getProductId() != null) {
            Predicate predicate = criteriaBuilder.equal(product.get("id"), search.getProductId());
            predicates.add(predicate);
        }
        if (search.getRating() != null) {
            Predicate predicate = criteriaBuilder.equal(root.get("rating"), search.getRating());
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

        // create query
        TypedQuery<Long> typedQuery = entityManager.createQuery(criteriaQuery.select(criteriaBuilder.count(root)));
        return typedQuery.getSingleResult();
    }
}
