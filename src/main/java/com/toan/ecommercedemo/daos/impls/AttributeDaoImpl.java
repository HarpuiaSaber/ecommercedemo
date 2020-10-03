package com.toan.ecommercedemo.daos.impls;

import com.toan.ecommercedemo.daos.AttributeDao;
import com.toan.ecommercedemo.entities.Attribute;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public class AttributeDaoImpl extends BaseDaoImpl<Attribute, Long> implements AttributeDao {
}
