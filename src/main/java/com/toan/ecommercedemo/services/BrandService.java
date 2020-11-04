package com.toan.ecommercedemo.services;

import com.toan.ecommercedemo.model.dto.BrandDto;
import com.toan.ecommercedemo.model.dto.TikiBrandDto;
import com.toan.ecommercedemo.model.search.ProductSearch;

import java.util.List;

public interface BrandService {

    public void addFromTiki(TikiBrandDto dto);

    public List<BrandDto> getOfProduct(ProductSearch search);
}
