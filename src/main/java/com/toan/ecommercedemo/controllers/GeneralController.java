package com.toan.ecommercedemo.controllers;

import com.toan.ecommercedemo.enums.ProductStatus;
import com.toan.ecommercedemo.enums.Role;
import com.toan.ecommercedemo.exceptions.InternalServerException;
import com.toan.ecommercedemo.model.UserPrincipal;
import com.toan.ecommercedemo.model.dto.ResponseDto;
import com.toan.ecommercedemo.model.dto.ShortProductDto;
import com.toan.ecommercedemo.model.dto.ViewProductDto;
import com.toan.ecommercedemo.model.search.ProductSearch;
import com.toan.ecommercedemo.services.ProductService;
import com.toan.ecommercedemo.services.RecommendService;
import com.toan.ecommercedemo.services.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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

    @Autowired
    ShopService shopService;

    @Autowired
    RecommendService recommendService;

    @GetMapping("/")
    public String root() {
        return "redirect:/swagger-ui.html";
    }

    @GetMapping("/home-direction")
    public String homeDirection() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.isAuthenticated()) {
            Object obj = authentication.getPrincipal();
            if (obj instanceof UserPrincipal) {
                UserPrincipal principal = (UserPrincipal) obj;
                if (principal.getRole() == Role.ADMIN) {
                    return "redirect:/admin/home";
                } else if (principal.getRole() == Role.SELLER) {
                    return "redirect:/seller/home";
                } else {
                    return "redirect:/home";
                }
            }
        }
        return "redirect:/login";
    }

    @GetMapping("/home")
    public String home(Model model) {
        return getViewName(model, "store/index");
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/signup")
    public String signup() {
        return "store/signup";
    }

    @GetMapping("/seller-signup")
    public String sellerSignup() {
        return "seller/signup";
    }

    @GetMapping("/search")
    public String search(Model model, @RequestParam(required = false) String name,
                         @RequestParam(required = false) Double fromPrice,
                         @RequestParam(required = false) Double toPrice,
                         @RequestParam(required = false) Long categoryId,
                         @RequestParam(required = false) Long brandId,
                         @RequestParam(required = false) Integer start) {
        model.addAttribute("name", name);
        model.addAttribute("fromPrice", fromPrice);
        model.addAttribute("toPrice", toPrice);
        model.addAttribute("categoryId", categoryId);
        if (categoryId == null) {
            model.addAttribute("childrenCategory", categoryService.getRoot());
        } else {
            model.addAttribute("childrenCategory", categoryService.getChildren(categoryId));
        }
        ProductSearch search = new ProductSearch();
        search.setName(name);
        search.setBrandId(brandId);
        search.setCategoryId(categoryId);
        search.setFromPrice(fromPrice);
        search.setToPrice(toPrice);
        search.setStart(start == null ? 0 : start);
        search.setLength(40);
        search.setStatus(ProductStatus.ACCEPTED.getValue());
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

    @GetMapping("/shop")
    public String shop(Model model, @RequestParam Long shopId,
                       @RequestParam(required = false) String name,
                       @RequestParam(required = false) Double fromPrice,
                       @RequestParam(required = false) Double toPrice,
                       @RequestParam(required = false) Long categoryId,
                       @RequestParam(required = false) Long brandId,
                       @RequestParam(required = false) Integer start) throws InternalServerException {
        model.addAttribute("name", name);
        model.addAttribute("fromPrice", fromPrice);
        model.addAttribute("toPrice", toPrice);
        model.addAttribute("shopCategoryId", categoryId);
        model.addAttribute("shopId", shopId);
        model.addAttribute("shop", shopService.getById(shopId));
        ProductSearch search = new ProductSearch();
        search.setShopId(shopId);
        search.setName(name);
        search.setBrandId(brandId);
        search.setCategoryId(categoryId);
        search.setFromPrice(fromPrice);
        search.setToPrice(toPrice);
        search.setStart(start == null ? 0 : start);
        search.setLength(40);
        search.setStatus(ProductStatus.ACCEPTED.getValue());
        List<ShortProductDto> dtos = productService.searchWithPaging(search);
        model.addAttribute("products", new ResponseDto<ShortProductDto>(productService.totalRecord(search), dtos.size(), dtos));
        return getViewName(model, "store/shop");
    }

    @GetMapping("/test-recommend/{id}")
    public String testReCommend(Model model, @PathVariable Long id) {
        List<ShortProductDto> dtos1 = productService.getCustomerProductInComment(id);
        model.addAttribute("products1", dtos1);
        //List<ShortProductDto> dtos2 = recommendService.getRecommendationOfCustomer(id, "/home/harpuiasaber/dataComment.csv");
        List<ShortProductDto> dtos2 = recommendService.getRecommendation(id);
        model.addAttribute("products2", dtos2);
        return getViewName(model, "store/test-recommend");
    }
}
