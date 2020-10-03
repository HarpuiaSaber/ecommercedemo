package com.toan.ecommercedemo.services;

import com.toan.ecommercedemo.model.dto.LocationDto;

import java.util.List;


public interface DistrictService extends BaseService<LocationDto, String> {

    public List<LocationDto> getByProvince(String provinceId);
}
