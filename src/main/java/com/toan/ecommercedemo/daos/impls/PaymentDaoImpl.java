package com.toan.ecommercedemo.daos.impls;

import com.toan.ecommercedemo.daos.PaymentDao;
import com.toan.ecommercedemo.entities.Payment;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public class PaymentDaoImpl extends BaseDaoImpl<Payment, Long> implements PaymentDao {
}
