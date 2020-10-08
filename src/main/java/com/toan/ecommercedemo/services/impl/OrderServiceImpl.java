package com.toan.ecommercedemo.services.impl;

import com.toan.ecommercedemo.daos.OrderDao;
import com.toan.ecommercedemo.services.OrderService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private OrderDao orderDao;

}
