package com.toan.ecommercedemo.services.impl;

import com.toan.ecommercedemo.daos.ItemDao;
import com.toan.ecommercedemo.daos.ProductDao;
import com.toan.ecommercedemo.entities.Item;
import com.toan.ecommercedemo.entities.Order;
import com.toan.ecommercedemo.entities.Product;
import com.toan.ecommercedemo.exceptions.InternalServerException;
import com.toan.ecommercedemo.model.dto.ItemDto;
import com.toan.ecommercedemo.model.dto.ItemStatisticDto;
import com.toan.ecommercedemo.services.ItemService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ItemServiceImpl implements ItemService {
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ItemDao itemDao;

    @Autowired
    private ProductDao productDao;

    @Override
    public void add(ItemDto dto) throws InternalServerException {
        Product original = productDao.getById(dto.getProduct().getId());
        if (original == null) {
            throw new InternalServerException("Không thấy sản phẩm");
        } else if (dto.getQuantity() == 0) {
            throw new InternalServerException("Hết hàng");
        } else if (original.getQuantity() < dto.getQuantity()) {
            throw new InternalServerException("Không đủ sản phẩm");
        } else {
            original.setQuantity(original.getQuantity() - dto.getQuantity());
            productDao.update(original);
            Item item = new Item();
            item.setOrder(new Order(dto.getOrderId()));
            item.setUnitPrice(dto.getUnitPrice());
            item.setQuantity(dto.getQuantity());
            item.setProduct(new Product(dto.getProduct().getId()));
            itemDao.add(item);
            dto.setId(item.getId());
        }
    }

    @Override
    public ItemStatisticDto getStatisticOfShop(Long shopId) {
        List<Item> entities = itemDao.getSoldItemOfShop(shopId);
        long totalSoldProduct = 0;
        double totalIncome = 0;
        for (Item item : entities) {
            totalSoldProduct += item.getQuantity();
            totalIncome += item.getQuantity() * item.getUnitPrice();
        }
        ItemStatisticDto dto = new ItemStatisticDto();
        dto.setTotalIncome(totalIncome);
        dto.setTotalSoldProduct(totalSoldProduct);
        return dto;
    }
}
