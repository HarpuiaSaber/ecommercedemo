package com.toan.ecommercedemo.services.impl;

import com.toan.ecommercedemo.daos.OrderDao;
import com.toan.ecommercedemo.entities.Contact;
import com.toan.ecommercedemo.entities.Order;
import com.toan.ecommercedemo.entities.Payment;
import com.toan.ecommercedemo.entities.User;
import com.toan.ecommercedemo.enums.OrderStatus;
import com.toan.ecommercedemo.model.dto.OrderDto;
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

    @Override
    public void add(OrderDto dto) {
        Order order = new Order();
        order.setPaid(dto.getPaid());
        order.setDescription(dto.getDescription());
        order.setDescription(dto.getDescription());
        order.setCustomer(new User(dto.getCustomerId()));
        order.setContact(new Contact(dto.getContactId()));
        order.setPayment(new Payment(dto.getPaymentId()));
        order.setStatus(OrderStatus.WAITTINGFORACCEPT);
        orderDao.add(order);
        dto.setId(order.getId());
    }
}
