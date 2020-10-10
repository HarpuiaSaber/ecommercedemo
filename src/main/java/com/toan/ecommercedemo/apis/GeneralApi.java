package com.toan.ecommercedemo.apis;

import com.toan.ecommercedemo.enums.Role;
import com.toan.ecommercedemo.exceptions.InternalServerException;
import com.toan.ecommercedemo.model.dto.AddUserDto;
import com.toan.ecommercedemo.model.dto.ResponseDto;
import com.toan.ecommercedemo.model.dto.ShortProductDto;
import com.toan.ecommercedemo.model.search.ProductSearch;
import com.toan.ecommercedemo.services.ProductService;
import com.toan.ecommercedemo.services.UserService;
import com.toan.ecommercedemo.utils.PasswordGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*", maxAge = -1)
public class GeneralApi {
    @Autowired
    private UserService userService;

    @Autowired
    private ProductService productService;

    @PostMapping("/createCustomer")
    @ResponseBody
    public AddUserDto createCustomer(@RequestBody AddUserDto dto) throws InternalServerException {
        dto.setRole(Role.CUSTOMER.getValue());
        dto.setPassword(PasswordGenerator.getHashString(dto.getPassword()));
        userService.add(dto);
        return dto;
    }

    @PostMapping("/searchProductPaging")
    @ResponseBody
    public ResponseDto<ShortProductDto> searchProductWithPaging(@RequestBody ProductSearch search) {
        List<ShortProductDto> dtos = productService.searchWithPaging(search);
        return new ResponseDto<ShortProductDto>(productService.totalRecord(search), dtos.size(), dtos);
    }
}
