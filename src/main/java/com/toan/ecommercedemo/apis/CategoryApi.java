package com.toan.ecommercedemo.apis;

import com.toan.ecommercedemo.model.dto.CategoryDto;
import com.toan.ecommercedemo.model.dto.ChildCategoryDto;
import com.toan.ecommercedemo.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@PreAuthorize("isAuthenticated()")
@RequestMapping("/api/category")
@CrossOrigin(origins = "*", maxAge = -1)
public class CategoryApi {

    @Autowired
    private CategoryService categoryService;

    @PreAuthorize("hasAnyAuthority('ADMIN', 'SELLER')")
    @GetMapping("/getChildren")
    @ResponseBody
    public List<ChildCategoryDto> getAll(@RequestParam(required = false) Long parentId) {
        return categoryService.getChildren(parentId);
    }

}
