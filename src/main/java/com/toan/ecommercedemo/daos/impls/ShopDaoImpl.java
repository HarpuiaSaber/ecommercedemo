package com.toan.ecommercedemo.daos.impls;

import com.toan.ecommercedemo.daos.ShopDao;
import com.toan.ecommercedemo.entities.Shop;
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
}
