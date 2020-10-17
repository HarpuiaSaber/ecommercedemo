package com.toan.ecommercedemo.controllers;

import com.toan.ecommercedemo.model.dto.ResponseDto;
import com.toan.ecommercedemo.model.dto.ShortProductDto;
import com.toan.ecommercedemo.model.dto.ViewProductDto;
import com.toan.ecommercedemo.model.search.ProductSearch;
import com.toan.ecommercedemo.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class GeneralController extends BaseController {

    @Autowired
    ProductService productService;

    @GetMapping("/")
    public String root() {
        return "redirect:/swagger-ui.html";
    }

    @GetMapping("/home")
    public String home(Model model) {
        return getViewName(model, "store/index");
    }

    @GetMapping("/login")
    public String login(Model model) {
        return getViewName(model, "store/login");
    }

    @GetMapping("/signup")
    public String signup(Model model) {
        return getViewName(model, "store/signup");
    }

    @GetMapping("/search")
    public String search(Model model, @RequestParam(required = false) String name,
                         @RequestParam(required = false) Double fromPrice,
                         @RequestParam(required = false) Double toPrice,
                         @RequestParam(required = false) Long categoryId,
                         @RequestParam(required = false) Long brandId,
                         @RequestParam(required = false) Integer start) {
        ProductSearch search = new ProductSearch();
        search.setName(name);
        search.setBrandId(brandId);
        search.setCategoryId(categoryId);
        search.setFromPrice(fromPrice);
        search.setToPrice(toPrice);
        search.setStart(start == null ? 0 : start);
        search.setLength(40);
        List<ShortProductDto> dtos = productService.searchWithPaging(search);
        model.addAttribute("products", new ResponseDto<ShortProductDto>(productService.totalRecord(search), dtos.size(), dtos));
        return getViewName(model, "store/search");
    }

    @GetMapping("/product/{id}")
    public String search(@PathVariable Long id, Model model) {
        ViewProductDto dto = productService.getById(id);
        model.addAttribute("product", dto);
        return getViewName(model, "store/product");
    }
}
