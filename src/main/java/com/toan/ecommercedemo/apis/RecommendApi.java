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
@RequestMapping("/api")
@CrossOrigin(origins = "", maxAge = -1)

public class RecommendApi {

    @Autowired
    RecommendService recommendService;

    @GetMapping("/getRecommendedItemOfCustomer")
    @ResponseBody
    public List<ShortProductDto> getCustomerRecommendedItem(@RequestParam Long userId) {
        return recommendService.getRecommendation(userId);
    }

    @GetMapping("/getRecommendedItemInMovieLens")
    @ResponseBody
    public List<Long> getRecommendedItemInMovieLens(@RequestParam Long userId, @RequestParam String path) {
        return recommendService.getRecommendationInMovieLens(userId, path);
    }

    @GetMapping("/createData")
    @ResponseBody
    public void createData() {
        recommendService.createData();
    }
}
