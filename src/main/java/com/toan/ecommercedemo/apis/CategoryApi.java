package com.toan.ecommercedemo.apis;

import com.toan.ecommercedemo.model.dto.CategoryDto;
import com.toan.ecommercedemo.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/category")
@CrossOrigin(origins = "*", maxAge = -1)
public class CategoryApi {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/getAll")
    @ResponseBody
    public List<CategoryDto> getAll() {
        return categoryService.getParentCategory();
    }
}
