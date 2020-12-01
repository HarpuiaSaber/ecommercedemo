package com.toan.ecommercedemo.services.impl;

import com.toan.ecommercedemo.daos.PaymentDao;
import com.toan.ecommercedemo.entities.Payment;
import com.toan.ecommercedemo.enums.PaymentMethod;
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

    private List<PaymentDto> paymentDtos;

    @Override
    public List<PaymentDto> getAll() {
        List<PaymentDto> dtos = new ArrayList<PaymentDto>();
        List<Payment> entities = paymentDao.getAll();
        for (Payment entity : entities) {
            PaymentDto paymentDto = modelMapper.map(entity, PaymentDto.class);
            //paymentDto.setMethod(entity.getMethod());
            dtos.add(paymentDto);
        }
        return dtos;
    }

    @Override
    public PaymentDto getById(Long id) {
        if (paymentDtos == null) {
            paymentDtos = this.getAll();
        }
        PaymentDto paymentDto = null;
        for (PaymentDto payment : paymentDtos) {
            if (payment.getId() == id) {
                paymentDto = payment;
                break;
            }
        }
        return paymentDto;
    }

    @Override
    public PaymentDto getByMethod(PaymentMethod method) {
        if (paymentDtos == null) {
            paymentDtos = this.getAll();
        }
        PaymentDto paymentDto = null;
        for (PaymentDto payment : paymentDtos) {
            if (payment.getMethod().equals(method)) {
                paymentDto = payment;
                break;
            }
        }
        return paymentDto;
    }

    @Override
    public List<PaymentDto> getPaymentDtos() {
        if (paymentDtos == null) {
            paymentDtos = this.getAll();
        }
        return paymentDtos;
    }
}
