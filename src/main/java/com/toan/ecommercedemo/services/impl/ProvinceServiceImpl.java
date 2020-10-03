package com.toan.ecommercedemo.services.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import com.toan.ecommercedemo.daos.ProvinceDao;
import com.toan.ecommercedemo.entities.Province;
import com.toan.ecommercedemo.model.dto.LocationDto;
import com.toan.ecommercedemo.services.ProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class ProvinceServiceImpl implements ProvinceService {

    @Autowired
    private ProvinceDao provinceDao;

    @Override
    public void add(LocationDto t) {

    }

    @Override
    public void update(LocationDto t) {

    }

    @Override
    public void delete(String k) {

    }

    @Override
    public LocationDto getById(String k) {
        return toDto(provinceDao.getById(k));
    }

    @Override
    public List<LocationDto> getAll() {
        List<LocationDto> dtos = new ArrayList<LocationDto>();
        List<Province> provinces = provinceDao.getAll();
        for (Province province : provinces) {
            dtos.add(toDto(province));
        }
        return dtos;
    }

    public LocationDto toDto(Province province) {
        LocationDto dto = new LocationDto();
        dto.setId(province.getId());
        dto.setName(province.getName());
        return dto;
    }

}
