package com.toan.ecommercedemo.services.impl;

import com.toan.ecommercedemo.daos.AttributeDao;
import com.toan.ecommercedemo.entities.Attribute;
import com.toan.ecommercedemo.entities.Product;
import com.toan.ecommercedemo.entities.Specification;
import com.toan.ecommercedemo.model.dto.TikiAttributeDto;
import com.toan.ecommercedemo.model.dto.TikiSpecificationDto;
import com.toan.ecommercedemo.services.AttributeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class AttributeServiceImpl implements AttributeService {
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private AttributeDao attributeDao;

    @Override
    public void addFromTiki(TikiSpecificationDto dto) {
        List<TikiAttributeDto> attributeDtos = dto.getAttributes();
        for (TikiAttributeDto attributeDto : attributeDtos) {
            Attribute attribute = modelMapper.map(attributeDto, Attribute.class);
            attribute.setSpecification(new Specification(dto.getId()));
            attributeDao.add(attribute);
        }

    }
}
