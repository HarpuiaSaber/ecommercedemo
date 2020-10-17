package com.toan.ecommercedemo.services.impl;

import com.toan.ecommercedemo.daos.CategoryDao;
import com.toan.ecommercedemo.entities.Category;
import com.toan.ecommercedemo.model.dto.CategoryDto;
import com.toan.ecommercedemo.services.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private CategoryDao categoryDao;

    @Override
    public List<CategoryDto> getParentCategory() {
        List<Category> entities = categoryDao.getParentCategory();
        List<CategoryDto> dtos = new ArrayList<CategoryDto>();
        for (Category entity : entities) {
            CategoryDto dto = modelMapper.map(entity, CategoryDto.class);
            dtos.add(dto);
        }
        return dtos;
    }

    @Override
    public List<CategoryDto> getChildrenCategory(Long parentId) {
        List<Category> entities = categoryDao.getChildenCategory(parentId);
        List<CategoryDto> dtos = new ArrayList<CategoryDto>();
        for (Category entity : entities) {
            CategoryDto dto = modelMapper.map(entity, CategoryDto.class);
            dtos.add(dto);
        }
        return dtos;
    }
}
