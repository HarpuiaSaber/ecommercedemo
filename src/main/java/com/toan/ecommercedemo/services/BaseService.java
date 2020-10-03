package com.toan.ecommercedemo.services;

import com.toan.ecommercedemo.exceptions.InternalServerException;

import java.util.List;

public interface BaseService<T, K> {

    void add(T t) throws InternalServerException;

    void update(T t) throws InternalServerException;

    void delete(K k) throws InternalServerException;

    T getById(K k) throws InternalServerException;

    List<T> getAll();
}
