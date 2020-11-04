package com.toan.ecommercedemo.services.impl;

import com.toan.ecommercedemo.daos.OrderDao;
import com.toan.ecommercedemo.daos.OrderHistoryDao;
import com.toan.ecommercedemo.entities.*;
import com.toan.ecommercedemo.enums.OrderStatus;
import com.toan.ecommercedemo.model.dto.OrderDto;
import com.toan.ecommercedemo.model.dto.TrackingOrderDto;
import com.toan.ecommercedemo.model.search.OrderSearch;
import com.toan.ecommercedemo.services.OrderService;
import com.toan.ecommercedemo.utils.DateTimeUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private OrderHistoryDao orderHistoryDao;

    @Override
    public void add(OrderDto dto) {
        Order order = new Order();
        order.setId(null);
        order.setPaid(dto.getPaid());
        order.setDescription(dto.getDescription());
        order.setDescription(dto.getDescription());
        order.setCustomer(new User(dto.getCustomerId()));
        order.setContact(new Contact(dto.getContactId()));
        order.setPayment(new Payment(dto.getPaymentId()));
        order.setStatus(OrderStatus.WAITINGFORACCEPT);
        orderDao.add(order);
        dto.setId(order.getId());
        //save to history
        OrderHistory orderHistory = new OrderHistory();
        orderHistory.setOrder(order);
        orderHistory.setStatus(order.getStatus());
        orderHistoryDao.add(orderHistory);
    }

    @Override
    public List<TrackingOrderDto> searchWithPaging(OrderSearch search) {
        List<Order> entities = orderDao.searchWithPaging(search);
        List<TrackingOrderDto> dtos = new ArrayList<TrackingOrderDto>();
        for (Order entity : entities) {
            TrackingOrderDto dto = new TrackingOrderDto();
            dto.setId(entity.getId());
            dto.setCreatedDate(DateTimeUtils.formatDate(entity.getCreatedDate(), DateTimeUtils.DD_MM_YYYY));
            dto.setStatus(entity.getStatus().getValue());
            List<Item> itemEntties = entity.getItems();
            List<String> itemDtos = new ArrayList<>();
            Double totalMoney = 0D;
            for (Item itemEntity : itemEntties) {
                totalMoney += itemEntity.getPrice() * itemEntity.getQuantity();
                itemDtos.add(itemEntity.getQuantity() + " × " + itemEntity.getProduct().getName());
            }
            dto.setItems(itemDtos);
            dto.setTotalMoney(totalMoney);
            dtos.add(dto);
        }
        return dtos;
    }

    @Override
    public Long getTotalRecord(OrderSearch search) {
        return orderDao.getTotalRecord(search);
    }

    @Override
    public Long getTotalOrderOfShop(Long shopId, Integer status) {
        return orderDao.getTotalOrderOfShop(shopId, status);
    }

    @Override
    public void updateStatus(Long orderId, Integer status) {
        Order old = orderDao.getById(orderId);
        old.setStatus(OrderStatus.valueOf(status));
        orderDao.update(old);
        OrderHistory orderHistory = new OrderHistory();
        orderHistory.setOrder(old);
        orderHistory.setStatus(old.getStatus());
        orderHistoryDao.update(orderHistory);
    }
}
