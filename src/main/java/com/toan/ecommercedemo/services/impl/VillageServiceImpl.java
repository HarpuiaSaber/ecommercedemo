package com.toan.ecommercedemo.services.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import com.toan.ecommercedemo.daos.VillageDao;
import com.toan.ecommercedemo.entities.Village;
import com.toan.ecommercedemo.model.dto.LocationDto;
import com.toan.ecommercedemo.services.VillageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class VillageServiceImpl implements VillageService {

    @Autowired
    private VillageDao villageDao;

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
        return toDto(villageDao.getById(k));
    }

    @Override
    public List<LocationDto> getAll() {
        List<LocationDto> dtos = new ArrayList<LocationDto>();
        List<Village> villages = villageDao.getAll();
        for (Village village : villages) {
            dtos.add(toDto(village));
        }
        return dtos;
    }

    @Override
    public List<LocationDto> getByDistrict(String districtId) {
        List<LocationDto> dtos = new ArrayList<LocationDto>();
        List<Village> villages = villageDao.getByDistrict(districtId);
        for (Village village : villages) {
            dtos.add(toDto(village));
        }
        return dtos;
    }

    public LocationDto toDto(Village village) {
        LocationDto dto = new LocationDto();
        dto.setId(village.getId());
        dto.setName(village.getName());
        return dto;
    }

}
