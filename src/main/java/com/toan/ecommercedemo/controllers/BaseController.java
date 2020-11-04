package com.toan.ecommercedemo.controllers;

import com.toan.ecommercedemo.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

@Controller
public abstract class BaseController {

    @Autowired
    CategoryService categoryService;

    protected String getViewName(Model model, String viewName) {
        model.addAttribute("categories", categoryService.getRoot());
        return viewName;
    }
}
