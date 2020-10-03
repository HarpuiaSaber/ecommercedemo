package com.toan.ecommercedemo.services;

import com.toan.ecommercedemo.exceptions.InternalServerException;
import com.toan.ecommercedemo.model.dto.*;
import com.toan.ecommercedemo.model.search.UserSearch;

import java.util.List;

public interface ShopService {
    public void add(SaveShopDto dto) throws InternalServerException;

    public void update(SaveShopDto dto) throws InternalServerException;

    public void delete(Long id) throws InternalServerException;

    public ViewShopDto getById(Long id) throws InternalServerException;

    public void addFromTiki(TikiSellerDto dto);
}
