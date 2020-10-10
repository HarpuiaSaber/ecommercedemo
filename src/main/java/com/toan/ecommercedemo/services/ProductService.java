package com.toan.ecommercedemo.services;

import com.toan.ecommercedemo.entities.Product;
import com.toan.ecommercedemo.exceptions.InternalServerException;
import com.toan.ecommercedemo.model.dto.ProductDto;
import com.toan.ecommercedemo.model.dto.ShortProductDto;
import com.toan.ecommercedemo.model.dto.TikiProductDto;
import com.toan.ecommercedemo.model.search.ProductSearch;

import java.util.List;

public interface ProductService {
    //    public void add() throws InternalServerException;
//
//    public void update() throws InternalServerException;
//
    public void delete(Long id) throws InternalServerException;

    public ProductDto getById(Long id) throws InternalServerException;

    public List<ShortProductDto> searchWithPaging(ProductSearch search);

    public Long totalRecord(ProductSearch search);

    public void addFromTiki(TikiProductDto dto);

    public List<Long> getAllId();
}
