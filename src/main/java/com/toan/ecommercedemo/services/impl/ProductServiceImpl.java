package com.toan.ecommercedemo.services.impl;

import com.toan.ecommercedemo.daos.*;
import com.toan.ecommercedemo.entities.*;
import com.toan.ecommercedemo.exceptions.InternalServerException;
import com.toan.ecommercedemo.model.dto.*;
import com.toan.ecommercedemo.model.search.ProductSearch;
import com.toan.ecommercedemo.services.ProductService;
import com.toan.ecommercedemo.utils.Constants;
import com.toan.ecommercedemo.utils.DateTimeUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ProductDao productDao;

    @Autowired
    private CategoryDao categoryDao;

    @Autowired
    private ShopDao shopDao;

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
    public ViewProductDto getById(Long id) {
        Product entity = productDao.getById(id);
        ViewProductDto dto = modelMapper.map(entity, ViewProductDto.class);
        dto.setBrand(entity.getBrand().getName());
        List<Comment> comments = entity.getComments();
        if (comments.size() > 0) {
            dto.setTotalComment(comments.size());
            double sumRating = 0;
            for (Comment comment : comments) {
                sumRating += comment.getRating();
            }
            dto.setRating((double) Math.round(sumRating / comments.size() * 10) / 10);
        } else {
            dto.setRating(0D);
            dto.setTotalComment(0);
        }
        List<ProductImage> imageEntities = entity.getImages();
        List<String> imageDtos = new ArrayList<>();
        for (ProductImage imageEntity : imageEntities) {
            if (imageEntity.getFromTiki())
                imageDtos.add(imageEntity.getPath());
            else {
                imageDtos.add(Constants.baseUrl + imageEntity.getPath());
            }
        }
        dto.setImages(imageDtos);
        List<Specification> specificationEntities = entity.getSpecifications();
        List<SpecificationDto> specificationDtos = new ArrayList<SpecificationDto>();
        for (Specification specificationEntity : specificationEntities) {
            SpecificationDto specificationDto = new SpecificationDto();
            specificationDto.setId(specificationEntity.getId());
            specificationDto.setName(specificationEntity.getName());
            List<Attribute> attributeEntities = specificationEntity.getAttributes();
            List<AttributeDto> attributeDtos = new ArrayList<AttributeDto>();
            for (Attribute attributeEntity : attributeEntities) {
                AttributeDto attributeDto = new AttributeDto();
                attributeDto.setId(attributeEntity.getId());
                attributeDto.setName(attributeEntity.getName());
                attributeDto.setValue(attributeEntity.getValue());
                attributeDtos.add((attributeDto));
            }
            specificationDto.setAttributes(attributeDtos);
            specificationDtos.add(specificationDto);
        }
        Shop shopEntity = entity.getShop();
        ViewShopDto shopDto = new ViewShopDto();
        shopDto.setId(shopEntity.getId());
        shopDto.setName(shopEntity.getName());
        shopDto.setDescription(shopEntity.getDescription());
        shopDto.setCreatedDate(DateTimeUtils.formatDate(shopEntity.getCreatedDate(), DateTimeUtils.DD_MM_YYYY));
        if (shopEntity.getFromTiki())
            shopDto.setLogo(shopEntity.getLogo());
        else {
            shopDto.setLogo(Constants.baseUrl + Constants.folderImage + Constants.folderShop + shopEntity.getLogo());
        }
        shopDto.setTotalProduct(shopDao.getTotalProduct(shopEntity.getId(), 2));
        shopDto.setTotalComment(shopDao.getTotalComment(shopEntity.getId()));
        dto.setShop(shopDto);
        return dto;
    }

    @Override
    public CartProductDto getCartProductById(Long id) {
        Product entity = productDao.getById(id);
        CartProductDto dto = modelMapper.map(entity, CartProductDto.class);
        List<ProductImage> imageEntities = entity.getImages();
        List<String> imageDtos = new ArrayList<>();
        for (ProductImage imageEntity : imageEntities) {
            if (imageEntity.getFromTiki())
                imageDtos.add(imageEntity.getPath());
            else {
                imageDtos.add(Constants.baseUrl + Constants.folderImage + Constants.folderProduct + imageEntity.getPath());
            }
        }
        dto.setShopId(entity.getShop().getId());
        dto.setImages(imageDtos);
        return dto;
    }

    @Override
    public List<ShortProductDto> searchWithPaging(ProductSearch search) {
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
        List<Product> entities = productDao.searchWithPaging(search);
        List<ShortProductDto> dtos = new ArrayList<>();
        for (Product entity : entities) {
            ShortProductDto dto = modelMapper.map(entity, ShortProductDto.class);
            List<ProductImage> imageEntities = entity.getImages();
//            List<String> imageDtos = new ArrayList<>();
//            for (ProductImage imageEntity : imageEntities) {
//                if (imageEntity.getFromTiki())
//                    imageDtos.add(imageEntity.getPath());
//                else {
//                    imageDtos.add(Constants.baseUrl + imageEntity.getPath());
//                }
//            }
//            dto.setImages(imageDtos);
            if (imageEntities.size() > 0) {
                ProductImage imageEntity = imageEntities.get(0);
                if (imageEntity.getFromTiki())
                    dto.setImage(imageEntity.getPath());
                else {
                    dto.setImage(Constants.baseUrl + Constants.folderImage + Constants.folderProduct + imageEntity.getPath());
                }
            }
            List<Comment> comments = entity.getComments();
            if (comments.size() > 0) {
                dto.setTotalComment(comments.size());
                double sumRating = 0;
                for (Comment comment : comments) {
                    sumRating += comment.getRating();
                }
                dto.setRating((double) Math.round(sumRating / comments.size() * 10) / 10);
            } else {
                dto.setRating(0);
                dto.setTotalComment(0);
            }
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

    @Override
    public List<CommentProductDto> getCustomerProductWithoutComment(Long customerId, Integer start, Integer length) {
        List<Product> entities = productDao.getCustomerProductWithoutComment(customerId, start, length);
        List<CommentProductDto> dtos = new ArrayList<CommentProductDto>();
        for (Product entity : entities) {
            CommentProductDto dto = modelMapper.map(entity, CommentProductDto.class);
            List<ProductImage> imageEntities = entity.getImages();
            if (imageEntities.size() > 0) {
                ProductImage imageEntity = imageEntities.get(0);
                if (imageEntity.getFromTiki())
                    dto.setImage(imageEntity.getPath());
                else {
                    dto.setImage(Constants.baseUrl + Constants.folderImage + Constants.folderProduct + imageEntity.getPath());
                }
            }
            dtos.add(dto);
        }
        return dtos;
    }

    @Override
    public Long countCustomerProductWithoutComment(Long customerId) {
        return productDao.countCustomerProductWithoutComment(customerId);
    }

    @Override
    public List<ShortProductDto> getCustomerProductInComment(Long customerId) {
        List<Product> entities = productDao.getCustomerProductInComment(customerId);
        List<ShortProductDto> dtos = new ArrayList<>();
        for (Product entity : entities) {
            ShortProductDto dto = modelMapper.map(entity, ShortProductDto.class);
            List<ProductImage> imageEntities = entity.getImages();
//            List<String> imageDtos = new ArrayList<>();
//            for (ProductImage imageEntity : imageEntities) {
//                if (imageEntity.getFromTiki())
//                    imageDtos.add(imageEntity.getPath());
//                else {
//                    imageDtos.add(Constants.baseUrl + imageEntity.getPath());
//                }
//            }
//            dto.setImages(imageDtos);
            if (imageEntities.size() > 0) {
                ProductImage imageEntity = imageEntities.get(0);
                if (imageEntity.getFromTiki())
                    dto.setImage(imageEntity.getPath());
                else {
                    dto.setImage(Constants.baseUrl + Constants.folderImage + Constants.folderProduct + imageEntity.getPath());
                }
            }
            List<Comment> comments = entity.getComments();
            if (comments.size() > 0) {
                dto.setTotalComment(comments.size());
                double sumRating = 0;
                for (Comment comment : comments) {
                    sumRating += comment.getRating();
                }
                dto.setRating((double) Math.round(sumRating / comments.size() * 10) / 10);
            } else {
                dto.setRating(0);
                dto.setTotalComment(0);
            }
            dtos.add(dto);
        }
        return dtos;
    }
}
