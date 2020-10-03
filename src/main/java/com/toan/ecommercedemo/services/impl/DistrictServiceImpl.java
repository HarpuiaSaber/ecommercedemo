package com.toan.ecommercedemo.services.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import com.toan.ecommercedemo.daos.DistrictDao;
import com.toan.ecommercedemo.entities.District;
import com.toan.ecommercedemo.exceptions.InternalServerException;
import com.toan.ecommercedemo.model.dto.LocationDto;
import com.toan.ecommercedemo.services.DistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class DistrictServiceImpl implements DistrictService {

    @Autowired
    private DistrictDao districtDao;


    @Override
    public void add(LocationDto locationDto) throws InternalServerException {

    }

    @Override
    public void update(LocationDto locationDto) throws InternalServerException {

    }

    @Override
    public void delete(String k) throws InternalServerException {

    }

    @Override
    public LocationDto getById(String k) throws InternalServerException {
        return toDto(districtDao.getById(k));
    }

    @Override
    public List<LocationDto> getAll() {
        List<LocationDto> dtos = new ArrayList<LocationDto>();
        List<District> districts = districtDao.getAll();
        for (District district : districts) {
            dtos.add(toDto(district));
        }
        return dtos;
    }


    @Override
    public List<LocationDto> getByProvince(String provinceId) {
        List<LocationDto> dtos = new ArrayList<LocationDto>();
        List<District> districts = districtDao.getByProvince(provinceId);
        for (District district : districts) {
            dtos.add(toDto(district));
        }
        return dtos;
    }

    public LocationDto toDto(District district) {
        LocationDto dto = new LocationDto();
        dto.setId(district.getId());
        dto.setName(district.getName());
        return dto;
    }

}
