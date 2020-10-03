package com.toan.ecommercedemo.services;

import com.toan.ecommercedemo.exceptions.InternalServerException;
import com.toan.ecommercedemo.model.dto.AddUserDto;
import com.toan.ecommercedemo.model.dto.TikiSellerDto;
import com.toan.ecommercedemo.model.dto.UpdateUserDto;
import com.toan.ecommercedemo.model.dto.ViewUserDto;
import com.toan.ecommercedemo.model.search.UserSearch;

import java.util.List;

public interface UserService {
    public void add(AddUserDto dto) throws InternalServerException;

    public void update(UpdateUserDto dto) throws InternalServerException;

    public void delete(Long id) throws InternalServerException;

    public ViewUserDto getById(Long id) throws InternalServerException;

    public List<ViewUserDto> search(UserSearch search);

    public void addSellerFromTiki(TikiSellerDto dto);
}
