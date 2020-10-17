package com.toan.ecommercedemo.apis;

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

    @Autowired
    private CommentService commentService;

    @Autowired
    private CommentImageService commentImageService;

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

    @PostMapping("/comment/add")
    @ResponseBody
    public void addComment(@RequestBody TikiCommentDto dto) {
        for (TikiCommentDataDto dataDto : dto.getData()) {
            userService.addCustommerFromTiki(dataDto.getCreated_by());
            commentService.addFromTiki(dataDto);
            commentImageService.addFromTiki(dataDto);
        }
    }

    @GetMapping("/product")
    @ResponseBody
    public ViewProductDto getProductById(@RequestParam long id) throws InternalServerException {
        return productService.getById(id);
    }

    @DeleteMapping("/product/delete")
    public void deleteProduct(@RequestParam long id) throws InternalServerException {
        productService.delete(id);
    }

}
