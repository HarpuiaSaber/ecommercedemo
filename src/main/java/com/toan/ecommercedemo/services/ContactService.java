package com.toan.ecommercedemo.services;

import com.toan.ecommercedemo.exceptions.InternalServerException;
import com.toan.ecommercedemo.model.dto.SaveContactDto;
import com.toan.ecommercedemo.model.dto.ViewContactDto;

import java.util.List;

public interface ContactService {
    public void add(SaveContactDto dto) throws InternalServerException;

    public void update(SaveContactDto dto) throws InternalServerException;

    public void delete(Long id) throws InternalServerException;

    public ViewContactDto getById(Long id) throws InternalServerException;

    public List<ViewContactDto> getContactOfUser(Long userId);
}
