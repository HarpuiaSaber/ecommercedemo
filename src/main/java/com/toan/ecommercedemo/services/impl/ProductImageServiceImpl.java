package com.toan.ecommercedemo.services.impl;

import com.toan.ecommercedemo.daos.ProductImageDao;
import com.toan.ecommercedemo.entities.Product;
import com.toan.ecommercedemo.entities.ProductImage;
import com.toan.ecommercedemo.model.dto.TikiBrandDto;
import com.toan.ecommercedemo.model.dto.TikiProductDto;
import com.toan.ecommercedemo.model.dto.TikiProductImageDto;
import com.toan.ecommercedemo.services.BrandService;
import com.toan.ecommercedemo.services.ProductImageService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ProductImageServiceImpl implements ProductImageService {
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ProductImageDao productImageDao;

    @Override
    public void addFromTiki(TikiProductDto dto) {
        List<TikiProductImageDto> productImageDtos = dto.getImages();
        for (TikiProductImageDto productImageDto : productImageDtos) {
            ProductImage productImage = new ProductImage();
            productImage.setFromTiki(true);
            productImage.setPath(productImageDto.getBase_url());
            productImage.setProduct(new Product(dto.getId()));
            productImageDao.add(productImage);
        }
    }
}
