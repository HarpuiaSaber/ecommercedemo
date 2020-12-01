package com.toan.ecommercedemo.daos.impls;

import com.toan.ecommercedemo.daos.TransactionDao;
import com.toan.ecommercedemo.entities.Transaction;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public class TransactionDaoImpl extends BaseDaoImpl<Transaction, Long> implements TransactionDao {
}
