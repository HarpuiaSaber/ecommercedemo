package com.toan.ecommercedemo.services;

import com.toan.ecommercedemo.model.dto.CategoryDto;

import java.util.List;

public interface CategoryService {

    public List<CategoryDto> getChildren(Long parentId);

    public CategoryDto getDetailCategory(Long parentId);
}
