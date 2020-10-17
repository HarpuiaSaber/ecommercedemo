package com.toan.ecommercedemo.apis;

import com.toan.ecommercedemo.model.dto.ResponseDto;
import com.toan.ecommercedemo.model.dto.ShortProductDto;
import com.toan.ecommercedemo.model.search.ProductSearch;
import com.toan.ecommercedemo.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*", maxAge = -1)
public class ProductApi {

    @Autowired
    private ProductService productService;

    @PostMapping("/searchProductPaging")
    @ResponseBody
    public ResponseDto<ShortProductDto> searchProductWithPaging(@RequestBody ProductSearch search) {
        List<ShortProductDto> dtos = productService.searchWithPaging(search);
        return new ResponseDto<ShortProductDto>(productService.totalRecord(search), dtos.size(), dtos);
    }
}
