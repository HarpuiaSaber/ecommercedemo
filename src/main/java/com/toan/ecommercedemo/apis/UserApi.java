package com.toan.ecommercedemo.apis;

import com.toan.ecommercedemo.model.dto.ResponseDto;
import com.toan.ecommercedemo.model.dto.ViewUserDto;
import com.toan.ecommercedemo.model.search.UserSearch;
import com.toan.ecommercedemo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@PreAuthorize("isAuthenticated()")
@RequestMapping("/api/user")
@CrossOrigin(origins = "", maxAge = -1)
public class UserApi {

    @Autowired
    private UserService userService;

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @PostMapping("/getAllPaging")
    @ResponseBody
    public ResponseDto<ViewUserDto> getBrandOfProduct(@RequestBody UserSearch search) {
        List<ViewUserDto> dtos = userService.searchWithPaging(search);
        return new ResponseDto<ViewUserDto>(dtos.size(), userService.getTotalRecord(search), dtos);
    }
}

