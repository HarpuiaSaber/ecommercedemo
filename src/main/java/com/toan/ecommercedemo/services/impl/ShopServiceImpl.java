package com.toan.ecommercedemo.services.impl;

import com.toan.ecommercedemo.daos.ShopDao;
import com.toan.ecommercedemo.entities.Contact;
import com.toan.ecommercedemo.entities.Shop;
import com.toan.ecommercedemo.entities.User;
import com.toan.ecommercedemo.exceptions.InternalServerException;
import com.toan.ecommercedemo.model.dto.*;
import com.toan.ecommercedemo.services.ShopService;
import com.toan.ecommercedemo.utils.DateTimeUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class ShopServiceImpl implements ShopService {
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ShopDao shopDao;


    @Override
    public void add(SaveShopDto dto) throws InternalServerException {
        //check sellerId ???
        Shop shop = modelMapper.map(dto, Shop.class);
        shop.setId(null);
        shopDao.add(shop);
        dto.setId(shop.getId());
    }

    @Override
    public void update(SaveShopDto dto) throws InternalServerException {
        Shop old = shopDao.getById(dto.getId());
        if (old != null) {
            old = modelMapper.map(dto, Shop.class);
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
            User sellerEntity = entity.getSeller();
            SellerDto sellerDto = new SellerDto();
            sellerDto.setId(sellerEntity.getId());
            sellerDto.setDob(DateTimeUtils.formatDate(sellerEntity.getDob(), DateTimeUtils.DD_MM_YYYY));
            return dto;
        } else {
            throw new InternalServerException("Shop không tồn tại");
        }
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
}
