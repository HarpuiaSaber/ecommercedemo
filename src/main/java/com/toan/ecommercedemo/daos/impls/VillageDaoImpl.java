package com.toan.ecommercedemo.daos.impls;

import com.toan.ecommercedemo.daos.VillageDao;
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
public class VillageDaoImpl extends BaseDaoImpl<Village, String> implements VillageDao {

    @Override
    public List<Village> getByDistrict(String districtId) {
        // create returns the data type of critetia query
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Village> criteriaQuery = criteriaBuilder.createQuery(Village.class);

        // from and join entity
        Root<Village> root = criteriaQuery.from(Village.class);
        Join<Village, District> district = root.join("district");

        // add predicate
        List<Predicate> predicates = new ArrayList<>();
        if (StringUtils.isNotBlank(districtId)) {
            Predicate predicate = criteriaBuilder.like(district.get("id"), districtId);
            predicates.add(predicate);
        }
        criteriaQuery.where(predicates.toArray(new Predicate[]{}));

        // create query
        TypedQuery<Village> typedQuery = entityManager.createQuery(criteriaQuery.select(root));

        return typedQuery.getResultList();
    }

}
