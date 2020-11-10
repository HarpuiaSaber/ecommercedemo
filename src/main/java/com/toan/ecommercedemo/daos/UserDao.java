package com.toan.ecommercedemo.daos;

import com.toan.ecommercedemo.entities.User;
import com.toan.ecommercedemo.model.search.UserSearch;

import java.util.List;

public interface UserDao extends BaseDao<User, Long> {
    public User getByUsername(String username);

    public User getByName(String name);

    public List<User> getAllPaging(UserSearch search);

    public Long getTotalRecord(UserSearch search);
}
