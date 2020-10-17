package com.toan.ecommercedemo.apis;

import com.toan.ecommercedemo.enums.Role;
import com.toan.ecommercedemo.exceptions.InternalServerException;
import com.toan.ecommercedemo.model.dto.AddUserDto;
import com.toan.ecommercedemo.services.UserService;
import com.toan.ecommercedemo.utils.PasswordGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*", maxAge = -1)
public class GeneralApi {
    @Autowired
    private UserService userService;

    @PostMapping("/createCustomer")
    @ResponseBody
    public AddUserDto createCustomer(@RequestBody AddUserDto dto) throws InternalServerException {
        dto.setRole(Role.CUSTOMER.getValue());
        dto.setPassword(PasswordGenerator.getHashString(dto.getPassword()));
        userService.add(dto);
        return dto;
    }
}
