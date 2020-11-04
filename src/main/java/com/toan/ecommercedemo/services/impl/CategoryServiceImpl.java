package com.toan.ecommercedemo.services.impl;

import com.toan.ecommercedemo.daos.CategoryDao;
import com.toan.ecommercedemo.entities.Category;
import com.toan.ecommercedemo.model.dto.CategoryDto;
import com.toan.ecommercedemo.model.dto.ChildCategoryDto;
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
    public List<CategoryDto> getRoot() {
        List<Category> entities = categoryDao.getParentCategory();
        List<CategoryDto> dtos = new ArrayList<CategoryDto>();
        for (Category entity : entities) {
            CategoryDto dto = modelMapper.map(entity, CategoryDto.class);
            dtos.add(dto);
        }
        return dtos;
    }

    @Override
    public CategoryDto getChildren(Long parentId) {
        List<Category> entities = null;
        CategoryDto dto = new CategoryDto();
        Category category = categoryDao.getById(parentId);
        if (category.getLeaf()) {
            Category parent = category.getParentCategory();
            dto.setId(parent.getId());
            dto.setName(parent.getName());
            entities = categoryDao.getChildenCategory(parent.getId());
        } else {
            dto.setId(category.getId());
            dto.setName(category.getName());
            entities = categoryDao.getChildenCategory(parentId);
        }
        List<ChildCategoryDto> children = new ArrayList<ChildCategoryDto>();
        for (Category entity : entities) {
            ChildCategoryDto childDto = modelMapper.map(entity, ChildCategoryDto.class);
            children.add(childDto);
        }
        dto.setChildren(children);
        return dto;
    }
}
