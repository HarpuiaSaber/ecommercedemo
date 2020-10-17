package com.toan.ecommercedemo.services;

import com.toan.ecommercedemo.model.dto.PaymentDto;

import java.util.List;

public interface PaymentService {

    List<PaymentDto> getAll();
}
