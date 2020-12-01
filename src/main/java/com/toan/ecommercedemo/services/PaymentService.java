package com.toan.ecommercedemo.services;

import com.toan.ecommercedemo.enums.PaymentMethod;
import com.toan.ecommercedemo.model.dto.PaymentDto;

import java.util.List;

public interface PaymentService {

    List<PaymentDto> getAll();

    PaymentDto getById(Long id);

    PaymentDto getByMethod(PaymentMethod method);

    public List<PaymentDto> getPaymentDtos();
}
