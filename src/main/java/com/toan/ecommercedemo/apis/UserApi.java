package com.toan.ecommercedemo.apis;

import com.toan.ecommercedemo.model.dto.ResponseDto;
import com.toan.ecommercedemo.model.dto.ViewUserDto;
import com.toan.ecommercedemo.model.search.UserSearch;
import com.toan.ecommercedemo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "", maxAge = -1)
public class UserApi {

    @Autowired
    private UserService userService;

    @PostMapping("/admin/user/getAllPaging")
    @ResponseBody
    public ResponseDto<ViewUserDto> getBrandOfProduct(@RequestBody UserSearch search) {
        List<ViewUserDto> dtos = userService.searchWithPaging(search);
        return new ResponseDto<ViewUserDto>(dtos.size(), userService.getTotalRecord(search), dtos);
    }
}

