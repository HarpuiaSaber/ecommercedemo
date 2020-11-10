package com.toan.ecommercedemo.services;

import com.toan.ecommercedemo.exceptions.InternalServerException;
import com.toan.ecommercedemo.model.dto.*;
import com.toan.ecommercedemo.model.search.UserSearch;

import java.util.List;

public interface UserService {
    public void add(AddUserDto dto) throws InternalServerException;

    public void update(UpdateUserDto dto) throws InternalServerException;

    public void delete(Long id) throws InternalServerException;

    public ViewUserDto getById(Long id) throws InternalServerException;

    public List<ViewUserDto> searchWithPaging(UserSearch search);

    public Long getTotalRecord(UserSearch search);

    public void addSellerFromTiki(TikiSellerDto dto);

    public void addCustommerFromTiki(TikiCustomerDto dto);
}
