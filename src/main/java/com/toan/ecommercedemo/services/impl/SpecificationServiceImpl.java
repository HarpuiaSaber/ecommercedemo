package com.toan.ecommercedemo.services.impl;

import com.toan.ecommercedemo.daos.ShopDao;
import com.toan.ecommercedemo.daos.SpecificationDao;
import com.toan.ecommercedemo.entities.Product;
import com.toan.ecommercedemo.entities.Shop;
import com.toan.ecommercedemo.entities.Specification;
import com.toan.ecommercedemo.entities.User;
import com.toan.ecommercedemo.exceptions.InternalServerException;
import com.toan.ecommercedemo.model.dto.*;
import com.toan.ecommercedemo.services.ShopService;
import com.toan.ecommercedemo.services.SpecificationService;
import com.toan.ecommercedemo.utils.DateTimeUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class SpecificationServiceImpl implements SpecificationService {
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private SpecificationDao specificationDao;


    @Override
    public void addFromTiki(TikiProductDto dto) {
        List<TikiSpecificationDto> specificationDtos = dto.getSpecifications();
        for (TikiSpecificationDto specificationDto : specificationDtos) {
            Specification specification = new Specification();
            specification.setName(specificationDto.getName());
            specification.setProduct(new Product(dto.getId()));
            specificationDao.add(specification);
            specificationDto.setId(specification.getId());
        }
    }
}
