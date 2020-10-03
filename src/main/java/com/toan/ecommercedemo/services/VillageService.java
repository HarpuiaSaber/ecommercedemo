package com.toan.ecommercedemo.services;

import java.util.List;

import com.toan.ecommercedemo.model.dto.LocationDto;

public interface VillageService extends BaseService<LocationDto, String> {

    public List<LocationDto> getByDistrict(String districtId);
}
