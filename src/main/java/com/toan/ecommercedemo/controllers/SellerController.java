package com.toan.ecommercedemo.controllers;

import com.toan.ecommercedemo.exceptions.InternalServerException;
import com.toan.ecommercedemo.model.UserPrincipal;
import com.toan.ecommercedemo.model.dto.ItemStatisticDto;
import com.toan.ecommercedemo.model.dto.SaveShopDto;
import com.toan.ecommercedemo.model.dto.UpdateLogoDto;
import com.toan.ecommercedemo.model.dto.ViewShopDto;
import com.toan.ecommercedemo.model.search.ProductSearch;
import com.toan.ecommercedemo.services.ItemService;
import com.toan.ecommercedemo.services.OrderService;
import com.toan.ecommercedemo.services.ShopService;
import com.toan.ecommercedemo.utils.Constants;
import com.toan.ecommercedemo.utils.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
@RequestMapping("/seller")
public class SellerController {

    @Autowired
    ShopService shopService;

    @Autowired
    OrderService orderService;

    @Autowired
    ItemService itemService;

    @GetMapping("/home")
    public String home(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.isAuthenticated()) {
            Object obj = authentication.getPrincipal();
            if (obj instanceof UserPrincipal) {
                UserPrincipal principal = (UserPrincipal) obj;
                ProductSearch search = new ProductSearch();
                ViewShopDto shopDto = shopService.getOfSeller(principal.getId());
                search.setShopId(shopDto.getId());
                model.addAttribute("totalAcceptedProduct", shopDto.getTotalProduct());
                model.addAttribute("totalSuccessOrder", orderService.getTotalOrderOfShop(shopDto.getId(), 3));
                ItemStatisticDto itemStatisticDto = itemService.getStatisticOfShop(shopDto.getId());
                model.addAttribute("totalSoldProduct", itemStatisticDto.getTotalSoldProduct());
                model.addAttribute("totalIncome", itemStatisticDto.getTotalIncome());
                model.addAttribute("totalComment", shopDto.getTotalComment());
                model.addAttribute("rating", shopDto.getRating());
                return "seller/index";
            }
        }
        return "redirect:/login";
    }

    @GetMapping("/shop/infor")
    public String shop(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.isAuthenticated()) {
            Object obj = authentication.getPrincipal();
            if (obj instanceof UserPrincipal) {
                UserPrincipal principal = (UserPrincipal) obj;
                ViewShopDto shopDto = shopService.getOfSeller(principal.getId());
                model.addAttribute("shop", shopDto);
                return "seller/shop/infor";
            }
        }
        return "redirect:/login";
    }

    @PostMapping("/shop/update-logo")
    public String updateLogo(@ModelAttribute UpdateLogoDto dto) throws InternalServerException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.isAuthenticated()) {
            Object obj = authentication.getPrincipal();
            if (obj instanceof UserPrincipal) {
                UserPrincipal principal = (UserPrincipal) obj;
                SaveShopDto shopDto = new SaveShopDto();
                shopDto.setSellerId(principal.getId());
                shopDto.setId(dto.getId());
                if (dto.getLogo() != null && !dto.getLogo().isEmpty()) {
                    shopDto.setLogo(FileUtils.saveFileWithSpecialNameByTime(dto.getLogo(), Constants.folderShop));
                }
                shopService.update(shopDto);
            }
        }
        return "redirect:/seller/shop/infor";
    }

    @PostMapping("/shop/update-description")
    public String updateDescription(@ModelAttribute SaveShopDto dto) throws InternalServerException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.isAuthenticated()) {
            Object obj = authentication.getPrincipal();
            if (obj instanceof UserPrincipal) {
                UserPrincipal principal = (UserPrincipal) obj;
                dto.setSellerId(principal.getId());
                shopService.update(dto);
            }
        }
        return "redirect:/seller/shop/infor";
    }

    @GetMapping("/product/list")
    public String listProduct(Model model) {
        return "seller/shop";
    }

    @GetMapping("/product/add")
    public String addProduct(Model model) {
        return "seller/shop";
    }

    @GetMapping("/order/list-waiting-for-accept")
    public String listWaitingForAcceptOrder() {
        return "seller/order/list-waiting-for-accept";
    }

    @GetMapping("/order/list-waiting-for-item")
    public String listWaitingForItemOrder() {
        return "seller/order/list-waiting-for-item";
    }

    @GetMapping("/order/list-delivering")
    public String listDeliveringOrder() {
        return "seller/order/list-delivering";
    }

    @GetMapping("/order/list-success")
    public String listSuccessOrder() {
        return "seller/order/list-success";
    }

    @GetMapping("/order/list-cancel")
    public String listCancelOrder() {
        return "seller/order/list-cancel";
    }
}
