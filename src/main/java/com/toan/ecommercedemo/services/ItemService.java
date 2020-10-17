package com.toan.ecommercedemo.services;

import com.toan.ecommercedemo.exceptions.InternalServerException;
import com.toan.ecommercedemo.model.dto.ItemDto;

public interface ItemService {
    public void add(ItemDto dto) throws InternalServerException;
}
