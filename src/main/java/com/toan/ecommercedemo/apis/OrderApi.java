package com.toan.ecommercedemo.apis;

import com.toan.ecommercedemo.enums.Role;
import com.toan.ecommercedemo.exceptions.InternalServerException;
import com.toan.ecommercedemo.model.dto.*;
import com.toan.ecommercedemo.model.UserPrincipal;
import com.toan.ecommercedemo.model.search.OrderSearch;
import com.toan.ecommercedemo.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@PreAuthorize("isAuthenticated()")
@RequestMapping("/api/order")
@CrossOrigin(origins = "", maxAge = -1)
public class OrderApi {

    @Autowired
    private OrderService orderService;

//    @PreAuthorize("hasAnyAuthority('CUSTOMER')")
//    @PostMapping("/add")
//    @ResponseBody
//    public Long order(@RequestBody OrderDto dto, HttpSession httpSession) throws InternalServerException {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        if (authentication.isAuthenticated()) {
//            Object obj = authentication.getPrincipal();
//            if (obj instanceof UserPrincipal) {
//                UserPrincipal principal = (UserPrincipal) obj;
//                dto.setCustomerId(principal.getId());
//                //set multi shop
//                Map<Long, ItemDto> map = (Map<Long, ItemDto>) httpSession.getAttribute("cart");
//                Set<Long> shopIds = new HashSet<Long>();
//                for (ItemDto itemDto : map.values()) {
//                    shopIds.add(itemDto.getProduct().getShopId());
//                }
//                //create multi order
//                PaymentDto paymentDto = paymentService.getById(dto.getPaymentId());
//                if (paymentDto.getMethod() == PaymentMethod.COD) {
//                    dto.setStatus(OrderStatus.WAITINGFORACCEPT);
//                } else if (paymentDto.getMethod() == PaymentMethod.PAYPAL) {
//                    dto.setStatus(OrderStatus.NOTPAID);
//                }
//                for (Long shopId : shopIds) {
//                    orderService.add(dto);
//                    for (ItemDto itemDto : map.values()) {
//                        if (itemDto.getProduct().getShopId() == shopId) {
//                            itemDto.setOrderId(dto.getId());
//                            itemService.add(itemDto);
//                        }
//                    }
//                }
//                httpSession.removeAttribute("cart");
//                return dto.getId();
//            }
//        }
//        throw new InternalServerException("Phiên đăng nhập hết hạn");
//    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'SELLER', 'CUSTOMER')")
    @PostMapping("/getAllPaging")
    @ResponseBody
    public ResponseDto<ShortOrderDto> getOrderAllPaging(@RequestBody OrderSearch search) throws InternalServerException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.isAuthenticated()) {
            Object obj = authentication.getPrincipal();
            if (obj instanceof UserPrincipal) {
                UserPrincipal principal = (UserPrincipal) obj;
                if (principal.getRole() == Role.CUSTOMER) {
                    search.setCustomerId(principal.getId());
                } else if (principal.getRole() == Role.SELLER) {
                    search.setSellerId(principal.getId());
                }
                List<ShortOrderDto> dtos = orderService.searchWithPaging(search);
                return new ResponseDto<ShortOrderDto>(dtos.size(), orderService.getTotalRecord(search), dtos);
            }
        }
        throw new InternalServerException("Phiên đăng nhập hết hạn");
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'SELLER')")
    @PostMapping("/updateStatus")
    @ResponseBody
    public void updateOrderStatus(@RequestBody UpdateStatusDto body) {
        for (Long orderId : body.getIds()) {
            orderService.updateStatus(orderId, body.getStatus(), body.getContent());
        }

    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'SELLER')")
    @GetMapping("/getById")
    @ResponseBody
    public ViewOrderDto getById(@RequestParam Long id) {
        return orderService.getById(id);
    }

}

