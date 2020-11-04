package com.toan.ecommercedemo.services.impl;

import com.toan.ecommercedemo.daos.BrandDao;
import com.toan.ecommercedemo.daos.CategoryDao;
import com.toan.ecommercedemo.daos.ProductDao;
import com.toan.ecommercedemo.entities.Brand;
import com.toan.ecommercedemo.entities.Category;
import com.toan.ecommercedemo.model.dto.BrandDto;
import com.toan.ecommercedemo.model.dto.TikiBrandDto;
import com.toan.ecommercedemo.model.search.ProductSearch;
import com.toan.ecommercedemo.services.BrandService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class BrandServiceImpl implements BrandService {
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private BrandDao brandDao;

    @Autowired
    private ProductDao productDao;

    @Autowired
    private CategoryDao categoryDao;

    @Override
    public void addFromTiki(TikiBrandDto dto) {
        //add brand
        Brand old = brandDao.getByName(dto.getName());
        if (old == null) {
            Brand brand = new Brand();
            brand.setName(dto.getName());
            brandDao.add(brand);
            dto.setId(brand.getId());
        } else {
            dto.setId(old.getId());
        }
    }

    @Override
    public List<BrandDto> getOfProduct(ProductSearch search) {
        if (search.getCategoryId() != null) {
            Category category = categoryDao.getById(search.getCategoryId());
            List<Long> categoryIds = null;
            //if category is not leaf -> return children
            if (category.getLeaf()) {
                categoryIds = new ArrayList<>();
                categoryIds.add(category.getId());
            }
            //else -> get all category with same parent
            else {
                categoryIds = categoryDao.getChildenCategory(category.getId())
                        .stream()
                        .map(s -> s.getId())
                        .collect(Collectors.toList());
            }
            search.setCategoryIds(categoryIds);
        }
        List<Brand> entites = brandDao.getOfProduct(search);
        List<BrandDto> dtos = new ArrayList<>();
        for (Brand entity : entites) {
            BrandDto dto = modelMapper.map(entity, BrandDto.class);
            search.setBrandId(entity.getId());
            dto.setTotalProduct(productDao.totalRecord(search));
            dtos.add(dto);
        }
        return dtos;
    }
}
