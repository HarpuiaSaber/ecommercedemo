package com.toan.ecommercedemo.services;

import com.toan.ecommercedemo.model.dto.CategoryDto;

import java.util.List;

public interface CategoryService {

    public List<CategoryDto> getParentCategory();

    public List<CategoryDto> getChildrenCategory(Long parentId);
}
