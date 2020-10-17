package com.toan.ecommercedemo.services.impl;

import com.toan.ecommercedemo.daos.PaymentDao;
import com.toan.ecommercedemo.entities.Payment;
import com.toan.ecommercedemo.model.dto.PaymentDto;
import com.toan.ecommercedemo.services.PaymentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    private PaymentDao paymentDao;

    @Override
    public List<PaymentDto> getAll() {
        List<PaymentDto> dtos = new ArrayList<PaymentDto>();
        List<Payment> entities = paymentDao.getAll();
        for (Payment entity : entities) {
            dtos.add(modelMapper.map(entity, PaymentDto.class));
        }
        return dtos;
    }

}
