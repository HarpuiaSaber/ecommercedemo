package com.toan.ecommercedemo.services.impl;

import com.toan.ecommercedemo.daos.OrderDao;
import com.toan.ecommercedemo.daos.OrderHistoryDao;
import com.toan.ecommercedemo.entities.*;
import com.toan.ecommercedemo.enums.OrderStatus;
import com.toan.ecommercedemo.model.dto.*;
import com.toan.ecommercedemo.model.search.OrderSearch;
import com.toan.ecommercedemo.services.OrderService;
import com.toan.ecommercedemo.utils.DateTimeUtils;
import org.apache.commons.lang.StringUtils;
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
        order.setCustomer(new User(dto.getCustomerId()));
        order.setContact(new Contact(dto.getContactId()));
        order.setPayment(new Payment(dto.getPaymentId()));
        order.setStatus(dto.getStatus());
        orderDao.add(order);
        dto.setId(order.getId());
        //save to history
        OrderHistory orderHistory = new OrderHistory();
        orderHistory.setOrder(order);
        orderHistory.setStatus(order.getStatus());
        orderHistory.setContent(order.getStatus().name());
        orderHistoryDao.add(orderHistory);
    }

    @Override
    public List<ShortOrderDto> searchWithPaging(OrderSearch search) {
        List<Order> entities = orderDao.searchWithPaging(search);
        List<ShortOrderDto> dtos = new ArrayList<ShortOrderDto>();
        for (Order entity : entities) {
            ShortOrderDto dto = new ShortOrderDto();
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
    public void updateStatus(Long orderId, Integer status, String content) {
        Order old = orderDao.getById(orderId);
        OrderStatus oldStatus = old.getStatus();
        old.setStatus(OrderStatus.valueOf(status));
        orderDao.update(old);
        OrderHistory orderHistory = new OrderHistory();
        orderHistory.setOrder(old);
        orderHistory.setStatus(old.getStatus());
        if (StringUtils.isNotBlank(content)) {
            orderHistory.setContent(content);
        } else {
            orderHistory.setContent(oldStatus.name() + " to " + old.getStatus().name());
        }
        orderHistoryDao.update(orderHistory);
    }

    @Override
    public ViewOrderDto getById(Long id) {
        Order order = orderDao.getById(id);
        ViewOrderDto dto = new ViewOrderDto();
        dto.setId(order.getId());
        dto.setCustomerName(order.getCustomer().getName());
        dto.setCustomerPhone(order.getCustomer().getPhone());
        dto.setStatus(order.getStatus().getValue());
        dto.setPaymentMethod(order.getPayment().getName());
        dto.setCreatedDate(DateTimeUtils.formatDate(order.getCreatedDate(), DateTimeUtils.DD_MM_YYYY_HH_MM));
        Contact contact = order.getContact();
        ViewContactDto contactDto = new ViewContactDto();
        dto.setId(contact.getId());
        contactDto.setName(contact.getName());
        contactDto.setAddress(contact.getAddress());
        contactDto.setPhone(contact.getPhone());
        contactDto.setLocation(String.format("%s, %s, %s",
                contact.getVillage().getName(),
                contact.getVillage().getDistrict().getName(),
                contact.getVillage().getDistrict().getProvince().getName()));
        dto.setContact(contactDto);
        List<OrderHistory> histories = order.getHistories();
        List<OrderHistoryDto> historyDtos = new ArrayList<>();
        for (OrderHistory history : histories) {
            OrderHistoryDto historyDto = new OrderHistoryDto();
            historyDto.setId(history.getId());
            historyDto.setCreatedDate(DateTimeUtils.formatDate(history.getCreatedDate(), DateTimeUtils.DD_MM_YYYY_HH_MM));
            historyDto.setStatus(history.getStatus().getValue());
            historyDto.setContent(history.getContent());
            historyDtos.add(historyDto);
        }
        dto.setHistories(historyDtos);
        return dto;
    }
}
