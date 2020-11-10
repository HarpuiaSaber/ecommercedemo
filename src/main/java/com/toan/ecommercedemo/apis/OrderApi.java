package com.toan.ecommercedemo.apis;

import com.toan.ecommercedemo.exceptions.InternalServerException;
import com.toan.ecommercedemo.model.UserPrincipal;
import com.toan.ecommercedemo.model.dto.ItemDto;
import com.toan.ecommercedemo.model.dto.OrderDto;
import com.toan.ecommercedemo.model.dto.ResponseDto;
import com.toan.ecommercedemo.model.dto.TrackingOrderDto;
import com.toan.ecommercedemo.model.search.OrderSearch;
import com.toan.ecommercedemo.services.ItemService;
import com.toan.ecommercedemo.services.OrderService;
import com.toan.ecommercedemo.services.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "", maxAge = -1)
public class OrderApi {

    @Autowired
    private OrderService orderService;

    @Autowired
    private ItemService itemService;

    @Autowired
    private ShopService shopService;

    @PostMapping("/customer/addOrder")
    @ResponseBody
    public Long order(@RequestBody OrderDto dto, HttpSession httpSession) throws InternalServerException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.isAuthenticated()) {
            Object obj = authentication.getPrincipal();
            if (obj instanceof UserPrincipal) {
                UserPrincipal principal = (UserPrincipal) obj;
                dto.setCustomerId(principal.getId());
                dto.setPaid(false);
                //check same shop
                Map<Long, ItemDto> map = (Map<Long, ItemDto>) httpSession.getAttribute("cart");
                Set<Long> shopIds = new HashSet<Long>();
                for (ItemDto itemDto : map.values()) {
                    shopIds.add(itemDto.getProduct().getShopId());
                }
                for (Long shopId : shopIds) {
                    orderService.add(dto);
                    for (ItemDto itemDto : map.values()) {
                        if (itemDto.getProduct().getShopId() == shopId) {
                            itemDto.setOrderId(dto.getId());
                            itemService.add(itemDto);
                        }
                    }
                }
                httpSession.removeAttribute("cart");
                return dto.getId();
            }
        }
        throw new InternalServerException("Phiên đăng nhập hết hạn");
    }

    @PostMapping("/customer/order/getAllPaging")
    @ResponseBody
    public ResponseDto<TrackingOrderDto> getOrderOfCustomer(@RequestBody OrderSearch search) throws InternalServerException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.isAuthenticated()) {
            Object obj = authentication.getPrincipal();
            if (obj instanceof UserPrincipal) {
                UserPrincipal principal = (UserPrincipal) obj;
                search.setCustomerId(principal.getId());
                List<TrackingOrderDto> dtos = orderService.searchWithPaging(search);
                return new ResponseDto<TrackingOrderDto>(dtos.size(), orderService.getTotalRecord(search), dtos);
            }
        }
        throw new InternalServerException("Phiên đăng nhập hết hạn");
    }

    @PostMapping("/seller/order/getAllPaging")
    @ResponseBody
    public ResponseDto<TrackingOrderDto> getOrderOfShop(@RequestBody OrderSearch search) throws InternalServerException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.isAuthenticated()) {
            Object obj = authentication.getPrincipal();
            if (obj instanceof UserPrincipal) {
                UserPrincipal principal = (UserPrincipal) obj;
                search.setSellerId(principal.getId());
                List<TrackingOrderDto> dtos = orderService.searchWithPaging(search);
                return new ResponseDto<TrackingOrderDto>(dtos.size(), orderService.getTotalRecord(search), dtos);
            }
        }
        throw new InternalServerException("Phiên đăng nhập hết hạn");
    }


    @GetMapping("/seller/order/updateStatus")
    @ResponseBody
    public void updateOrderStatus(@RequestParam Long orderId, @RequestParam Integer status) {
        orderService.updateStatus(orderId, status);
    }
}

