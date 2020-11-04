package com.toan.ecommercedemo.daos.impls;

import com.toan.ecommercedemo.daos.ItemDao;
import com.toan.ecommercedemo.entities.Item;
import com.toan.ecommercedemo.entities.Product;
import com.toan.ecommercedemo.entities.Shop;
import com.toan.ecommercedemo.enums.OrderStatus;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Repository
@Transactional
public class ItemDaoImpl extends BaseDaoImpl<Item, Long> implements ItemDao {
    @Override
    public List<Item> getSoldItemOfShop(Long shopId) {
        // create returns the data type of critetia query
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Item> criteriaQuery = criteriaBuilder.createQuery(Item.class);

        // from and join entity
        Root<Item> root = criteriaQuery.from(Item.class);
        Join<Item, Product> product = root.join("product");
        Join<Product, Shop> shop = product.join("shop");
        Join<Item, Order> order = root.join("order");

        // add predicate
        List<Predicate> predicates = new ArrayList<>();
        if (shopId != null) {
            Predicate predicate = criteriaBuilder.equal(shop.get("id"), shopId);
            predicates.add(predicate);
        }
        Predicate predicate = criteriaBuilder.equal(order.get("status"), OrderStatus.SUCCESS);
        predicates.add(predicate);
        criteriaQuery.where(predicates.toArray(new Predicate[]{}));

        // create query
        TypedQuery<Item> typedQuery = entityManager.createQuery(criteriaQuery.select(root));
        return typedQuery.getResultList();
    }
}
