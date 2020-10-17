package com.toan.ecommercedemo.daos.impls;

import com.toan.ecommercedemo.daos.CategoryDao;
import com.toan.ecommercedemo.entities.Category;
import com.toan.ecommercedemo.entities.District;
import com.toan.ecommercedemo.entities.Village;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Repository
@Transactional
public class CategoryDaoImpl extends BaseDaoImpl<Category, Long> implements CategoryDao {
    @Override
    public List<Category> getParentCategory() {
        // create returns the data type of critetia query
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Category> criteriaQuery = criteriaBuilder.createQuery(Category.class);

        // from and join entity
        Root<Category> root = criteriaQuery.from(Category.class);

        // add predicate
        List<Predicate> predicates = new ArrayList<>();
        Predicate predicate = criteriaBuilder.isNull(root.get("parentCategory"));
        predicates.add(predicate);
        criteriaQuery.where(predicates.toArray(new Predicate[]{}));

        // create query
        TypedQuery<Category> typedQuery = entityManager.createQuery(criteriaQuery.select(root));

        return typedQuery.getResultList();
    }

    @Override
    public List<Category> getChildenCategory(Long parentId) {
        // create returns the data type of critetia query
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Category> criteriaQuery = criteriaBuilder.createQuery(Category.class);

        // from and join entity
        Root<Category> root = criteriaQuery.from(Category.class);
        Join<Category, Category> parent = root.join("parentCategory");

        // add predicate
        List<Predicate> predicates = new ArrayList<>();
        if (parentId != null) {
            Predicate predicate = criteriaBuilder.equal(parent.get("id"), parentId);
            predicates.add(predicate);
        }
        criteriaQuery.where(predicates.toArray(new Predicate[]{}));

        // create query
        TypedQuery<Category> typedQuery = entityManager.createQuery(criteriaQuery.select(root));

        return typedQuery.getResultList();
    }
}
