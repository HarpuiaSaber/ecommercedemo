package com.toan.ecommercedemo.daos.impls;

import com.toan.ecommercedemo.daos.RecommendDao;
import com.toan.ecommercedemo.entities.DataForRecommendation;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public class RecommendDaoImpl extends BaseDaoImpl<DataForRecommendation, Long> implements RecommendDao {
}
