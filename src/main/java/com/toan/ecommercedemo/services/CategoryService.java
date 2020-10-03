package com.toan.ecommercedemo.services;

import com.toan.ecommercedemo.model.dto.ViewCategoryDto;

import java.util.List;

public interface CategoryService {

    public List<ViewCategoryDto> getParentCategory();
}
