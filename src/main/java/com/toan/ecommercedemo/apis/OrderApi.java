package com.toan.ecommercedemo.apis;

import com.toan.ecommercedemo.exceptions.InternalServerException;
import com.toan.ecommercedemo.model.UserPrincipal;
import com.toan.ecommercedemo.model.dto.ItemDto;
import com.toan.ecommercedemo.model.dto.OrderDto;
import com.toan.ecommercedemo.services.ItemService;
import com.toan.ecommercedemo.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Map;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "", maxAge = -1)
public class OrderApi {

    @Autowired
    private OrderService orderService;

    @Autowired
    private ItemService itemService;

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
                orderService.add(dto);
                Map<Long, ItemDto> map = (Map<Long, ItemDto>) httpSession.getAttribute("cart");
                for (ItemDto itemDto : map.values()) {
                    itemDto.setOrderId(dto.getId());
                    itemService.add(itemDto);
                }
                httpSession.removeAttribute("cart");
                return dto.getId();
            }
        }
        throw new InternalServerException("Phiên đăng nhập hết hạn");
    }
}

