package com.toan.ecommercedemo.services.impl;

import com.toan.ecommercedemo.daos.*;
import com.toan.ecommercedemo.entities.*;
import com.toan.ecommercedemo.exceptions.InternalServerException;
import com.toan.ecommercedemo.model.dto.*;
import com.toan.ecommercedemo.services.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ProductDao productDao;

    @Override
    public ProductDto getById(Long id) throws InternalServerException {
        Product entity = productDao.getById(id);
        return modelMapper.map(entity, ProductDto.class);
    }

    @Override
    public void addFromTiki(TikiProductDto dto) {
        Product product = new Product();
        product.setId(dto.getId());
        product.setName(dto.getName().trim());
        product.setUnitPrice(dto.getPrice());
        product.setUnitPriceUSD(dto.getPrice_usd());
        product.setBrand(new Brand(dto.getBrand().getId()));
        product.setShop(new Shop(dto.getCurrent_seller().getStore_id()));
        product.setDescription(String.join("\n", dto.getTop_features()));
        product.setQuantity(1000);
        //set category
        product.setCategory(new Category(2L));
        productDao.add(product);

    }
}
