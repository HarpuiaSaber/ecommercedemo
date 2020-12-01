package com.toan.ecommercedemo.daos.impls;

import com.toan.ecommercedemo.daos.OrderHistoryDao;
import com.toan.ecommercedemo.entities.*;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public class OrderHistoryDaoImpl extends BaseDaoImpl<OrderHistory, Long> implements OrderHistoryDao {

}
