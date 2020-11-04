package com.toan.ecommercedemo.apis;

import com.toan.ecommercedemo.exceptions.InternalServerException;
import com.toan.ecommercedemo.model.UserPrincipal;
import com.toan.ecommercedemo.model.dto.ShortProductDto;
import com.toan.ecommercedemo.model.dto.UpdateUserDto;
import com.toan.ecommercedemo.services.RecommendService;
import com.toan.ecommercedemo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customer")
@CrossOrigin(origins = "", maxAge = -1)

public class CustomerApi {

    @Autowired
    UserService userService;

    @Autowired
    RecommendService recommendService;

    @PostMapping("/updateProfile")
    @ResponseBody
    public UpdateUserDto updateProfile(@RequestBody UpdateUserDto dto) throws InternalServerException {
        userService.update(dto);
        return dto;
    }

    @GetMapping("/getRecommendedItem")
    @ResponseBody
    public List<ShortProductDto> getCustomerRecommendedItem() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.isAuthenticated()) {
            Object obj = authentication.getPrincipal();
            if (obj instanceof UserPrincipal) {
                UserPrincipal principal = (UserPrincipal) obj;
                return recommendService.getRecommendation(principal.getId());
            }
        }
        return null;
    }
}
