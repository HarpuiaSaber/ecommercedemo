package com.toan.ecommercedemo.services.impl;

import com.toan.ecommercedemo.daos.CategoryDao;
import com.toan.ecommercedemo.entities.Category;
import com.toan.ecommercedemo.model.dto.ViewCategoryDto;
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
    public List<ViewCategoryDto> getParentCategory() {
        List<Category> entities = categoryDao.getParentCategory();
        List<ViewCategoryDto> dtos = new ArrayList<ViewCategoryDto>();
        for (Category entity : entities) {
            ViewCategoryDto dto = new ViewCategoryDto();
            dto.setId(entity.getId());
            dto.setName(entity.getName());
            List<Category> children = entity.getChildrenCategory();
            List<ViewCategoryDto> childrendto = new ArrayList<ViewCategoryDto>();
            for (Category child : children) {
                ViewCategoryDto childdto = new ViewCategoryDto();
                childdto.setId(child.getId());
                childdto.setName(child.getName());
                childrendto.add(childdto);
            }
            dto.setChildren(childrendto);
            dtos.add(dto);
        }
        return dtos;
    }
}
