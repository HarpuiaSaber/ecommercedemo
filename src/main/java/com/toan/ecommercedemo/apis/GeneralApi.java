package com.toan.ecommercedemo.apis;

import com.toan.ecommercedemo.enums.Role;
import com.toan.ecommercedemo.exceptions.InternalServerException;
import com.toan.ecommercedemo.model.dto.AddSellerDto;
import com.toan.ecommercedemo.model.dto.AddUserDto;
import com.toan.ecommercedemo.model.dto.SaveShopDto;
import com.toan.ecommercedemo.services.ShopService;
import com.toan.ecommercedemo.services.UserService;
import com.toan.ecommercedemo.utils.PasswordGenerator;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*", maxAge = -1)
public class GeneralApi {
    @Autowired
    private UserService userService;

    @Autowired
    private ShopService shopService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping("/createCustomer")
    @ResponseBody
    @ExceptionHandler(InternalServerException.class)
    public AddUserDto createCustomer(@RequestBody AddUserDto dto) throws InternalServerException {
        dto.setRole(Role.CUSTOMER.getValue());
        dto.setPassword(PasswordGenerator.getHashString(dto.getPassword()));
        userService.add(dto);
        return dto;
    }

    @PostMapping("/createSeller")
    @ResponseBody
    public AddSellerDto createSeller(@RequestBody AddSellerDto dto) throws InternalServerException {
        dto.setRole(Role.SELLER.getValue());
        dto.setPassword(PasswordGenerator.getHashString(dto.getPassword()));
        AddUserDto addUserDto = modelMapper.map(dto, AddUserDto.class);
        userService.add(addUserDto);
        SaveShopDto saveShopDto = new SaveShopDto();
        saveShopDto.setName(dto.getShopName());
        saveShopDto.setSellerId(addUserDto.getId());
        shopService.add(saveShopDto);
        return dto;
    }
}
