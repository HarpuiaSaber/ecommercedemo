package com.toan.ecommercedemo.daos.impls;

import com.toan.ecommercedemo.daos.ContactDao;
import com.toan.ecommercedemo.entities.Contact;
import com.toan.ecommercedemo.entities.User;
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
public class ContactDaoImpl extends BaseDaoImpl<Contact, Long> implements ContactDao {
    @Override
    public List<Contact> getContactOfUser(Long userId) {
        // create returns the data type of critetia query
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Contact> criteriaQuery = criteriaBuilder.createQuery(Contact.class);

        // from and join entity
        Root<Contact> root = criteriaQuery.from(Contact.class);
        Join<Contact, User> user = root.join("user");
        // add predicate
        List<Predicate> predicates = new ArrayList<>();
        if (userId != null) {
            Predicate predicate = criteriaBuilder.equal(user.get("id"), userId);
            predicates.add(predicate);
        }
        criteriaQuery.where(predicates.toArray(new Predicate[]{}));

        // create query
        TypedQuery<Contact> typedQuery = entityManager.createQuery(criteriaQuery.select(root));
        return typedQuery.getResultList();
    }
}

