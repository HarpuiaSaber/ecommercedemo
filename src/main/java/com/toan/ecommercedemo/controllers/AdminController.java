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

    @GetMapping("/user/list-shop")
    public String listSeller(Model model) {
        return "admin/user/list-shop";
    }

    @GetMapping("/user/list-user")
    public String listCustomer(Model model) {
        return "admin/user/list-user";
    }

    @GetMapping("/product/list-waiting-for-accept")
    public String listProduct() {
        return "admin/product/list-waiting-for-accept";
    }

    @GetMapping("/product/list-accepted")
    public String listAccepted() {
        return "admin/product/list-accepted";
    }

    @GetMapping("/product/list-deny")
    public String listCancel() {
        return "admin/product/list-deny";
    }

}
