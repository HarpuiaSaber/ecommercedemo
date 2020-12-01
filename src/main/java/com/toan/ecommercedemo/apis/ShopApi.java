package com.toan.ecommercedemo.apis;

import com.toan.ecommercedemo.exceptions.InternalServerException;
import com.toan.ecommercedemo.model.dto.ResponseDto;
import com.toan.ecommercedemo.model.dto.ShopDto;
import com.toan.ecommercedemo.model.dto.ViewShopDto;
import com.toan.ecommercedemo.model.search.ShopSearch;
import com.toan.ecommercedemo.services.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/shop")
@CrossOrigin(origins = "", maxAge = -1)
public class ShopApi {

    @Autowired
    private ShopService shopService;

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @GetMapping("/getById")
    @ResponseBody
    public ViewShopDto getShopById(@RequestParam Long id) throws InternalServerException {
        return shopService.getById(id);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @PostMapping("/getAllPaging")
    @ResponseBody
    public ResponseDto<ShopDto> addProduct(@RequestBody ShopSearch search) {
        List<ShopDto> dtos = shopService.getAllPaging(search);
        return new ResponseDto<ShopDto>(dtos.size(), shopService.getTotalRecord(search), dtos);
    }
}

