package com.toan.ecommercedemo.daos.impls;

import com.toan.ecommercedemo.daos.UserDao;
import com.toan.ecommercedemo.entities.User;
import com.toan.ecommercedemo.enums.Role;
import com.toan.ecommercedemo.model.search.UserSearch;
import com.toan.ecommercedemo.utils.DateTimeUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Repository
@Transactional
public class UserDaoImpl extends BaseDaoImpl<User, Long> implements UserDao {

    @Override
    public User getByUsername(String username) {
        // create returns the data type of critetia query
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);

        // from and join entity
        Root<User> root = criteriaQuery.from(User.class);

        // add predicate
        List<Predicate> predicates = new ArrayList<>();
        if (StringUtils.isNotBlank(username)) {
            Predicate predicate = criteriaBuilder.equal(root.get("username"), username);
            predicates.add(predicate);
        }
        criteriaQuery.where(predicates.toArray(new Predicate[]{}));

        // create query
        TypedQuery<User> typedQuery = entityManager.createQuery(criteriaQuery.select(root));
        try {
            return typedQuery.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public User getByName(String name) {
        // create returns the data type of critetia query
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);

        // from and join entity
        Root<User> root = criteriaQuery.from(User.class);

        // add predicate
        List<Predicate> predicates = new ArrayList<>();
        if (StringUtils.isNotBlank(name)) {
            Predicate predicate = criteriaBuilder.equal(root.get("name"), name);
            predicates.add(predicate);
        }
        criteriaQuery.where(predicates.toArray(new Predicate[]{}));

        // create query
        TypedQuery<User> typedQuery = entityManager.createQuery(criteriaQuery.select(root));
        try {
            return typedQuery.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public List<User> getAllPaging(UserSearch search) {
        // create returns the data type of critetia query
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);

        // from and join entity
        Root<User> root = criteriaQuery.from(User.class);

        // add predicate
        List<Predicate> predicates = new ArrayList<>();
        String key = search.getKey();
        if (StringUtils.isNotBlank(key)) {
            Predicate predicate = criteriaBuilder.or(criteriaBuilder.like(root.get("name"), "%" + key + "%"),
                    criteriaBuilder.like(root.get("username"), "%" + key + "%"),
                    criteriaBuilder.like(root.get("phone"), "%" + key + "%"),
                    criteriaBuilder.like(root.get("email"), "%" + key + "%"));
            predicates.add(predicate);
        }
        if (search.getRole() != null) {
            Predicate predicate = criteriaBuilder.equal(root.get("role"), Role.valueOf(search.getRole()));
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
        criteriaQuery.orderBy(criteriaBuilder.desc(root.get("createdDate")));

        // create query
        TypedQuery<User> typedQuery = entityManager.createQuery(criteriaQuery.select(root));
        if (search.getStart() != null) {
            typedQuery.setFirstResult((search.getStart()));
        }

        typedQuery.setMaxResults(search.getLength());
        return typedQuery.getResultList();
    }

    @Override
    public Long getTotalRecord(UserSearch search) {
        // create returns the data type of critetia query
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);

        // from and join entity
        Root<User> root = criteriaQuery.from(User.class);

        // add predicate
        List<Predicate> predicates = new ArrayList<>();
        String key = search.getKey();
        if (StringUtils.isNotBlank(key)) {
            Predicate predicate = criteriaBuilder.or(criteriaBuilder.like(root.get("name"), "%" + key + "%"),
                    criteriaBuilder.like(root.get("username"), "%" + key + "%"),
                    criteriaBuilder.like(root.get("phone"), "%" + key + "%"),
                    criteriaBuilder.like(root.get("email"), "%" + key + "%"));
            predicates.add(predicate);
        }
        if (search.getRole() != null) {
            Predicate predicate = criteriaBuilder.equal(root.get("role"), Role.valueOf(search.getRole()));
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
        try {
            return typedQuery.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
}
