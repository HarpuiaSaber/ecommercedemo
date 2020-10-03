package com.toan.ecommercedemo.daos;

import com.toan.ecommercedemo.entities.User;

public interface UserDao extends BaseDao<User, Long> {
    public User getByUsername(String username);
    public User getByName(String name);
}
