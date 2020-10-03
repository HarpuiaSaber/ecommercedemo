package com.toan.ecommercedemo.apis;

import com.toan.ecommercedemo.entities.Product;
import com.toan.ecommercedemo.exceptions.InternalServerException;
import com.toan.ecommercedemo.model.dto.*;
import com.toan.ecommercedemo.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
@CrossOrigin(origins = "", maxAge = -1)
public class AdminApi {
    @Autowired
    private UserService userService;

    @Autowired
    private BrandService brandService;

    @Autowired
    private ShopService shopService;

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductImageService productImageService;

    @Autowired
    private SpecificationService specificationService;

    @Autowired
    private AttributeService attributeService;

    @PostMapping("/addUser")
    @ResponseBody
    public AddUserDto addUser(@RequestBody AddUserDto dto) throws InternalServerException {
        userService.add(dto);
        return dto;
    }

    @GetMapping("/test")
    @ResponseBody
    public String test() throws InternalServerException {
        return "pelpleple";
    }

    @PostMapping("/product/add")
    @ResponseBody
    public void addProduct(@RequestBody TikiProductDto dto) {
        brandService.addFromTiki(dto.getBrand());
        userService.addSellerFromTiki(dto.getCurrent_seller());
        shopService.addFromTiki(dto.getCurrent_seller());
        productService.addFromTiki(dto);
        productImageService.addFromTiki(dto);
        specificationService.addFromTiki(dto);
        for (TikiSpecificationDto specificationDto : dto.getSpecifications()) {
            attributeService.addFromTiki(specificationDto);
        }
    }

    @GetMapping("/product")
    @ResponseBody
    public ProductDto getProductById(@RequestParam long id) throws InternalServerException {
        return productService.getById(id);
    }

}
