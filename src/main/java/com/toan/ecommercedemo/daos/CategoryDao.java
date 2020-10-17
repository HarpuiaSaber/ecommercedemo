package com.toan.ecommercedemo.daos;

import com.toan.ecommercedemo.entities.Category;

import java.util.List;

public interface CategoryDao extends BaseDao<Category, Long> {
    public List<Category> getParentCategory();

    public List<Category> getChildenCategory(Long parentId);
}
