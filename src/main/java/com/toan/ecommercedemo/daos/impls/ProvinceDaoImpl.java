package com.toan.ecommercedemo.daos.impls;

import com.toan.ecommercedemo.daos.ProvinceDao;
import com.toan.ecommercedemo.entities.Province;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public class ProvinceDaoImpl extends BaseDaoImpl<Province, String> implements ProvinceDao {

}
