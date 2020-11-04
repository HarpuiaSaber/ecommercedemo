package com.toan.ecommercedemo.apis;

import com.toan.ecommercedemo.model.dto.BrandDto;
import com.toan.ecommercedemo.model.search.ProductSearch;
import com.toan.ecommercedemo.services.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/brand")
@CrossOrigin(origins = "", maxAge = -1)
public class BrandApi {

    @Autowired
    private BrandService brandService;

    @PostMapping("/getOfProduct")
    @ResponseBody
    public List<BrandDto> getBrandOfProduct(@RequestBody ProductSearch search) {
        return brandService.getOfProduct(search);
    }
}

