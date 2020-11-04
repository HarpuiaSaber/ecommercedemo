package com.toan.ecommercedemo.controllers;

import com.toan.ecommercedemo.exceptions.InternalServerException;
import com.toan.ecommercedemo.model.UserPrincipal;
import com.toan.ecommercedemo.model.dto.ItemDto;
import com.toan.ecommercedemo.model.dto.ViewContactDto;
import com.toan.ecommercedemo.services.ContactService;
import com.toan.ecommercedemo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
@RequestMapping("/customer")
public class CustomerController extends BaseController {

    @Autowired
    UserService userService;

    @Autowired
    private ContactService contactService;

    @GetMapping("/profile")
    public String getProfile(Model model) throws InternalServerException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.isAuthenticated()) {
            Object obj = authentication.getPrincipal();
            if (obj instanceof UserPrincipal) {
                UserPrincipal principal = (UserPrincipal) obj;
                model.addAttribute("customer", userService.getById(principal.getId()));
                return getViewName(model, "store/customer");
            }
        }
        return getViewName(model, "store/login");
    }

    @GetMapping("/contact")
    public String getContactOfCustomer(Model model) {
        return getViewName(model, "store/list-contact");
    }

    @GetMapping("/order")
    public String getOrderOfCustomer(Model model) {
        return getViewName(model, "store/list-order");
    }

    @GetMapping("/comment")
    public String getCommentOfCustomer(Model model) {
        return getViewName(model, "store/list-comment");
    }

    @GetMapping("/cart")
    public String getCartOfCustomer(Model model) {
        return getViewName(model, "store/cart");
    }

    @GetMapping("/check-out-contact")
    public String checkOutContact(Model model, HttpSession httpSession) {
        Object object = httpSession.getAttribute("cart");
        if (object != null && ((Map<Long, ItemDto>) object).size() > 0) {
            return "store/check-out-contact";
        } else {
            return getViewName(model, "store/cart");
        }
    }

    @GetMapping("/check-out-payment")
    public String checkOutPayment(@RequestParam Long contactId, Model model, HttpSession httpSession) {
        Object object = httpSession.getAttribute("cart");
        if (object != null && ((Map<Long, ItemDto>) object).size() > 0) {
            try {
                ViewContactDto dto = contactService.getById(contactId);
                model.addAttribute("contact", dto);
                return "store/check-out-payment";
            } catch (Exception e) {
                return "redirect:/customer/check-out-contact";
            }
        } else {
            return getViewName(model, "store/cart");
        }
    }

}
