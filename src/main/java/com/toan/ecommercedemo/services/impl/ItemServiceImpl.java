package com.toan.ecommercedemo.services.impl;

import com.toan.ecommercedemo.daos.ItemDao;
import com.toan.ecommercedemo.services.ItemService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class ItemServiceImpl implements ItemService {
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ItemDao itemDao;

}
