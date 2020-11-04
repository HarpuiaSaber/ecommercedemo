package com.toan.ecommercedemo.controllers;

import com.toan.ecommercedemo.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private ProductService productService;

    @GetMapping("/crawler")
    public String crawler() {
        return "crawler";
    }

    @GetMapping("/crawler-comment")
    public String crawlerComment(Model model) {
        List<Long> productIds = productService.getAllId();
        StringBuilder builder = new StringBuilder();
        String prefix = "";
        for (Long id : productIds) {
            builder.append(prefix);
            prefix = ",";
            builder.append(id);
        }
        model.addAttribute("listId", builder.toString());
        return "crawlerComment";
    }

    @GetMapping("/home")
    public String home(Model model) {
        return "admin/index";
    }
}
