package com.toan.ecommercedemo.services.impl;

import com.toan.ecommercedemo.daos.*;
import com.toan.ecommercedemo.entities.*;
import com.toan.ecommercedemo.exceptions.InternalServerException;
import com.toan.ecommercedemo.model.dto.*;
import com.toan.ecommercedemo.model.search.ProductSearch;
import com.toan.ecommercedemo.services.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ProductDao productDao;

    @Autowired
    private ProductImageDao productImageDao;

    @Autowired
    private SpecificationDao specificationDao;

    @Autowired
    private AttributeDao attributeDao;

    @Override
    public void delete(Long id) throws InternalServerException {
        Product old = productDao.getById(id);
        if (old != null) {
            //delete image
            List<ProductImage> images = old.getImages();
            for (ProductImage image : images) {
                productImageDao.delete(image);
            }
            //delete specification
            List<Specification> specifications = old.getSpecifications();
            for (Specification specification : specifications) {
                //delete attribute
                List<Attribute> attributes = specification.getAttributes();
                for (Attribute attribute : attributes) {
                    attributeDao.delete(attribute);
                }
                specificationDao.delete(specification);
            }
            productDao.delete(old);
        } else {
            throw new InternalServerException("Sản phẩm không tồn tại!!!");
        }
    }

    @Override
    public ProductDto getById(Long id) throws InternalServerException {
        Product entity = productDao.getById(id);
        return modelMapper.map(entity, ProductDto.class);
    }

    @Override
    public List<ShortProductDto> searchWithPaging(ProductSearch search) {
        List<Product> entities = productDao.searchWithPaging(search);
        List<ShortProductDto> dtos = new ArrayList<>();
        for (Product entity : entities) {
            ShortProductDto dto = modelMapper.map(entity, ShortProductDto.class);
            List<ProductImage> imageEntities = entity.getImages();
            List<ProductImageDto> imageDtos = new ArrayList<>();
            for (ProductImage imageEntity : imageEntities) {
                ProductImageDto imageDto = modelMapper.map(imageEntity, ProductImageDto.class);
                imageDtos.add(imageDto);
            }
            dto.setImages(imageDtos);
            dtos.add(dto);
        }
        return dtos;
    }

    @Override
    public Long totalRecord(ProductSearch search) {
        return productDao.totalRecord(search);
    }

    @Override
    public void addFromTiki(TikiProductDto dto) {
        Product product = new Product();
        product.setId(dto.getId());
        product.setName(dto.getName().trim());
        product.setUnitPrice(dto.getPrice());
        product.setUnitPriceUSD(dto.getPrice_usd());
        product.setBrand(new Brand(dto.getBrand().getId()));
        product.setShop(new Shop(dto.getCurrent_seller().getStore_id()));
        product.setDescription(String.join("\n", dto.getTop_features()));
        product.setQuantity(1000);
        //set category
        product.setCategory(new Category(37L));
        productDao.add(product);

    }

    @Override
    public List<Long> getAllId() {
        return productDao.getAllId();
    }
}
