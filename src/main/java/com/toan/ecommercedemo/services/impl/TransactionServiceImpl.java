package com.toan.ecommercedemo.services.impl;

import com.toan.ecommercedemo.daos.TransactionDao;
import com.toan.ecommercedemo.entities.Order;
import com.toan.ecommercedemo.entities.Transaction;
import com.toan.ecommercedemo.model.dto.TransactionDto;
import com.toan.ecommercedemo.services.TransactionService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class TransactionServiceImpl implements TransactionService {
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private TransactionDao transactionDao;

    @Override
    public void add(TransactionDto dto) {
        Transaction transaction = modelMapper.map(dto, Transaction.class);
        transaction.setOrder(new Order(dto.getOrderId()));
        transactionDao.add(transaction);
        dto.setId(transaction.getId());
    }
}
