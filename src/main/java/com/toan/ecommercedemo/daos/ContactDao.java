package com.toan.ecommercedemo.daos;

import com.toan.ecommercedemo.entities.Contact;

import java.util.List;

public interface ContactDao extends BaseDao<Contact, Long> {
    public List<Contact> getContactOfUser(Long userId);
}
