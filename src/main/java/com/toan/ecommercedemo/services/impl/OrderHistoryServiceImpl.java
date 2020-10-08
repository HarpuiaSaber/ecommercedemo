package com.toan.ecommercedemo.services.impl;

import com.toan.ecommercedemo.daos.OrderHistoryDao;
import com.toan.ecommercedemo.services.OrderHistoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class OrderHistoryServiceImpl implements OrderHistoryService {
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private OrderHistoryDao orderHistoryDao;

}
