package com.toan.ecommercedemo.services;

import com.toan.ecommercedemo.entities.Product;
import com.toan.ecommercedemo.exceptions.InternalServerException;
import com.toan.ecommercedemo.model.dto.ProductDto;
import com.toan.ecommercedemo.model.dto.TikiProductDto;

import java.util.List;

public interface ProductService {
    //    public void add() throws InternalServerException;
//
//    public void update() throws InternalServerException;
//
    public void delete(Long id) throws InternalServerException;

    //
    public ProductDto getById(Long id) throws InternalServerException;
//
//    public void search() throws InternalServerException;

    public void addFromTiki(TikiProductDto dto);

    public List<Long> getAllId();
}
