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

    @GetMapping("/account/list-shop")
    public String listSeller(Model model) {
        return "admin/account/list-shop";
    }

    @GetMapping("/account/list-user")
    public String listCustomer(Model model) {
        return "admin/account/list-user";
    }

    @GetMapping("category/list")
    public String listCategory() {
        return "admin/category/list-category";
    }

    @GetMapping("/product/list-waiting-for-accept")
    public String listWaitingForAcceptProduct() {
        return "admin/product/list-waiting-for-accept";
    }

    @GetMapping("/product/list-accepted")
    public String listAcceptedProduct() {
        return "admin/product/list-accepted";
    }

    @GetMapping("/product/list-deny")
    public String listDenyProduct() {
        return "admin/product/list-deny";
    }

    @GetMapping("/order/list-waiting-for-accept")
    public String listWaitingForAcceptOrder() {
        return "admin/order/list-waiting-for-accept";
    }

    @GetMapping("/order/list-waiting-for-item")
    public String listWaitingForItemOrder() {
        return "admin/order/list-waiting-for-item";
    }

    @GetMapping("/order/list-delivering")
    public String listDeliveringOrder() {
        return "admin/order/list-delivering";
    }

    @GetMapping("/order/list-success")
    public String listSuccessOrder() {
        return "admin/order/list-success";
    }

    @GetMapping("/order/list-cancel")
    public String listCancelOrder() {
        return "admin/order/list-cancel";
    }

    @GetMapping("/order/list-not-paid")
    public String listNotPaid() {
        return "admin/order/list-not-paid";
    }

}
