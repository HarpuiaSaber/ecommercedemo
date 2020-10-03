package com.toan.ecommercedemo.daos.impls;

import com.toan.ecommercedemo.daos.SpecificationDao;
import com.toan.ecommercedemo.entities.Specification;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public class SpecificationDaoImpl extends BaseDaoImpl<Specification, Long> implements SpecificationDao {
}
