package com.toan.ecommercedemo.services;

import com.toan.ecommercedemo.exceptions.InternalServerException;
import com.toan.ecommercedemo.model.dto.ItemDto;
import com.toan.ecommercedemo.model.dto.ItemStatisticDto;

public interface ItemService {
    public void add(ItemDto dto) throws InternalServerException;

    public ItemStatisticDto getStatisticOfShop(Long shopId);
}
