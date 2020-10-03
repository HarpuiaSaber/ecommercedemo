package com.toan.ecommercedemo.daos.impls;

import com.toan.ecommercedemo.daos.ItemDao;
import com.toan.ecommercedemo.entities.Item;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public class ItemDaoImpl extends BaseDaoImpl<Item, Long> implements ItemDao {
}
