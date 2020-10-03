package com.toan.ecommercedemo.daos;

import com.toan.ecommercedemo.entities.District;

import java.util.List;

public interface DistrictDao extends BaseDao<District, String> {

    public List<District> getByProvince(String provinceId);
}
