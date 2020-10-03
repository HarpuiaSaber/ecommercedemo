package com.toan.ecommercedemo.daos;

import com.toan.ecommercedemo.entities.Village;

import java.util.List;

public interface VillageDao extends BaseDao<Village, String> {

    public List<Village> getByDistrict(String districtId);
}
