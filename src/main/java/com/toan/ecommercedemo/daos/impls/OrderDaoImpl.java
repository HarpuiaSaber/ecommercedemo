package com.toan.ecommercedemo.daos.impls;

import com.toan.ecommercedemo.daos.OrderDao;
import com.toan.ecommercedemo.entities.Order;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public class OrderDaoImpl extends BaseDaoImpl<Order, Long> implements OrderDao {
}
