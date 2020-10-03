package com.toan.ecommercedemo.services.impl;

import com.toan.ecommercedemo.daos.BrandDao;
import com.toan.ecommercedemo.entities.Brand;
import com.toan.ecommercedemo.model.dto.TikiBrandDto;
import com.toan.ecommercedemo.services.BrandService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class BrandServiceImpl implements BrandService {
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private BrandDao brandDao;

    @Override
    public void addFromTiki(TikiBrandDto dto) {
        //add brand
        Brand old = brandDao.getByName(dto.getName());
        if (old == null) {
            Brand brand = new Brand();
            brand.setName(dto.getName());
            brandDao.add(brand);
            dto.setId(brand.getId());
        } else{
            dto.setId(old.getId());
        }
    }
}
