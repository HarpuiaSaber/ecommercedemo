package com.toan.ecommercedemo.services;

import com.toan.ecommercedemo.model.dto.CategoryDto;
import com.toan.ecommercedemo.model.dto.ChildCategoryDto;

import java.util.List;

public interface CategoryService {

    public List<ChildCategoryDto> getChildren(Long parentId);

    public CategoryDto getDetailCategory(Long parentId);
}
