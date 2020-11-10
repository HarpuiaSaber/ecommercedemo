package com.toan.ecommercedemo.services.impl;

import com.google.common.base.Strings;
import com.toan.ecommercedemo.daos.ShopDao;
import com.toan.ecommercedemo.entities.Contact;
import com.toan.ecommercedemo.entities.Shop;
import com.toan.ecommercedemo.entities.User;
import com.toan.ecommercedemo.exceptions.InternalServerException;
import com.toan.ecommercedemo.model.dto.*;
import com.toan.ecommercedemo.model.search.ShopSearch;
import com.toan.ecommercedemo.services.ShopService;
import com.toan.ecommercedemo.utils.Constants;
import com.toan.ecommercedemo.utils.DateTimeUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class ShopServiceImpl implements ShopService {
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ShopDao shopDao;

    @Override
    public void add(SaveShopDto dto) throws InternalServerException {
        Shop shop = modelMapper.map(dto, Shop.class);
        shop.setId(null);
        shop.setFromTiki(false);
        shopDao.add(shop);
        dto.setId(shop.getId());
    }

    @Override
    public void update(SaveShopDto dto) throws InternalServerException {
        Shop old = shopDao.getById(dto.getId());
        if (old != null) {
            if (!old.getSeller().getId().equals(dto.getSellerId())) {
                throw new InternalServerException("Shop không phải của bạn");
            }
            if (!Strings.isNullOrEmpty(dto.getLogo())) {
                old.setLogo(dto.getLogo());
            }
            if (!Strings.isNullOrEmpty(dto.getDescription())) {
                old.setDescription(dto.getDescription());
            }
            shopDao.update(old);
        } else {
            throw new InternalServerException("Shop không tồn tại");
        }
    }

    @Override
    public void delete(Long id) throws InternalServerException {
        Shop old = shopDao.getById(id);
        if (old != null) {
            shopDao.delete(old);
        } else {
            throw new InternalServerException("Shop không tồn tại");
        }
    }

    @Override
    public ViewShopDto getById(Long id) throws InternalServerException {
        Shop entity = shopDao.getById(id);
        if (entity != null) {
            ViewShopDto dto = new ViewShopDto();
            dto.setId(entity.getId());
            dto.setName(entity.getName());
            dto.setDescription(entity.getDescription());
            dto.setCreatedDate(DateTimeUtils.formatDate(entity.getCreatedDate(), DateTimeUtils.DD_MM_YYYY));
            if (entity.getFromTiki())
                dto.setLogo(entity.getLogo());
            else {
                dto.setLogo(Constants.baseUrl + Constants.folderImage + Constants.folderShop + entity.getLogo());
            }
            dto.setTotalProduct(shopDao.getTotalProduct(entity.getId(), 2));
            long totalComment = shopDao.getTotalComment(entity.getId());
            if (totalComment > 0) {
                dto.setTotalComment(totalComment);
                dto.setRating((double) Math.round((double) shopDao.getTotalRating(entity.getId()) / totalComment * 10) / 10);
            } else {
                dto.setTotalComment(0);
                dto.setRating(0);
            }
            return dto;
        } else {
            throw new InternalServerException("Shop không tồn tại");
        }
    }

    @Override
    public ViewShopDto getOfSeller(Long sellerId) {
        Shop entity = shopDao.getOfSeller(sellerId);
        ViewShopDto dto = new ViewShopDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setDescription(entity.getDescription());
        dto.setCreatedDate(DateTimeUtils.formatDate(entity.getCreatedDate(), DateTimeUtils.DD_MM_YYYY));
        if (entity.getFromTiki())
            dto.setLogo(entity.getLogo());
        else {
            dto.setLogo(Constants.baseUrl + Constants.folderImage + Constants.folderShop + entity.getLogo());
        }
        dto.setTotalProduct(shopDao.getTotalProduct(entity.getId(), 2));
        long totalComment = shopDao.getTotalComment(entity.getId());
        if (totalComment > 0) {
            dto.setTotalComment(totalComment);
            dto.setRating((double) Math.round((double) shopDao.getTotalRating(entity.getId()) / totalComment * 10) / 10);
        } else {
            dto.setTotalComment(0);
            dto.setRating(0);
        }
        return dto;
    }

    @Override
    public Long getShopIdOfSeller(Long sellerId) {
        return shopDao.getOfSeller(sellerId).getId();
    }

    @Override
    public void addFromTiki(TikiSellerDto dto) {
        Shop oldShop = shopDao.getByName(dto.getName());
        if (oldShop == null) {
            Shop shop = new Shop();
            shop.setName(dto.getName().trim());
            shop.setLogo(dto.getLogo());
            shop.setSeller(new User(dto.getId()));
            shopDao.add(shop);
            dto.setStore_id(shop.getId());
        } else {
            dto.setStore_id(oldShop.getId());
        }
    }

    @Override
    public List<ShopDto> getAllPaging(ShopSearch search) {
        List<Shop> entities = shopDao.getAllPaging(search);
        List<ShopDto> dtos = new ArrayList<>();
        for (Shop entity : entities) {
            ShopDto dto =modelMapper.map(entity, ShopDto.class);
            if (entity.getFromTiki())
                dto.setLogo(entity.getLogo());
            else {
                dto.setLogo(Constants.baseUrl + Constants.folderImage + Constants.folderShop + entity.getLogo());
            }
            User seller = entity.getSeller();
            dto.setSellerEmail(seller.getEmail());
            dto.setSellerName(seller.getName());
            dto.setSellerUsername(seller.getUsername());
            dto.setSellerPhone(seller.getPhone());
            dtos.add(dto);
        }
        return dtos;
    }

    @Override
    public Long getTotalRecord(ShopSearch search) {
        return shopDao.getTotalRecord(search);
    }
}
