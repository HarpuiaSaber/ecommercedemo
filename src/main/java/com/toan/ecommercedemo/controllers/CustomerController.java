package com.toan.ecommercedemo.controllers;

import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;
import com.toan.ecommercedemo.enums.OrderStatus;
import com.toan.ecommercedemo.enums.PaymentMethod;
import com.toan.ecommercedemo.enums.PaypalPaymentIntent;
import com.toan.ecommercedemo.enums.PaypalPaymentMethod;
import com.toan.ecommercedemo.exceptions.InternalServerException;
import com.toan.ecommercedemo.model.UserPrincipal;
import com.toan.ecommercedemo.model.dto.*;
import com.toan.ecommercedemo.services.*;
import com.toan.ecommercedemo.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Controller
@RequestMapping("/customer")
public class CustomerController extends BaseController {

    @Autowired
    UserService userService;

    @Autowired
    PaypalService paypalService;

    @Autowired
    OrderService orderService;

    @Autowired
    PaymentService paymentService;

    @Autowired
    TransactionService transactionService;

    @Autowired
    ItemService itemService;

    @Autowired
    ContactService contactService;

    @GetMapping("/profile")
    public String getProfile(Model model) throws InternalServerException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.isAuthenticated()) {
            Object obj = authentication.getPrincipal();
            if (obj instanceof UserPrincipal) {
                UserPrincipal principal = (UserPrincipal) obj;
                model.addAttribute("customer", userService.getById(principal.getId()));
                return getViewName(model, "customer/account/info");
            }
        }
        return "/login";
    }

    @GetMapping("/contact")
    public String getContactOfCustomer(Model model) {
        return getViewName(model, "customer/contact/list-contact");
    }

    @GetMapping("/order")
    public String getOrderOfCustomer(Model model) {
        return getViewName(model, "customer/order/list-order");
    }

    @GetMapping("/comment")
    public String getCommentOfCustomer(Model model) {
        return getViewName(model, "customer/comment/list-comment");
    }

    @GetMapping("/cart")
    public String getCartOfCustomer(Model model) {
        return getViewName(model, "customer/cart/cart");
    }

    @GetMapping("/check-out-contact")
    public String checkOutContact(Model model, HttpSession httpSession) {
        Object object = httpSession.getAttribute("cart");
        if (object != null && ((Map<Long, ItemDto>) object).size() > 0) {
            return "customer/order/check-out-contact";
        } else {
            return getViewName(model, "customer/cart/cart");
        }
    }

    @GetMapping("/check-out-payment")
    public String checkOutPayment(@RequestParam Long contactId, Model model, HttpSession httpSession) {
        Object object = httpSession.getAttribute("cart");
        if (object != null && ((Map<Long, ItemDto>) object).size() > 0) {
            try {
                ViewContactDto dto = contactService.getById(contactId);
                model.addAttribute("contact", dto);
                return "customer/order/check-out-payment";
            } catch (Exception e) {
                return "redirect:/customer/check-out-contact";
            }
        } else {
            return getViewName(model, "customer/cart/cart");
        }
    }

    @PostMapping(value = "/order/create")
    public String createPayment(HttpSession httpSession, @ModelAttribute OrderDto dto) throws InternalServerException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.isAuthenticated()) {
            Object obj = authentication.getPrincipal();
            if (obj instanceof UserPrincipal) {
                UserPrincipal principal = (UserPrincipal) obj;
                dto.setCustomerId(principal.getId());
                //split multi shop
                Map<Long, ItemDto> map = (Map<Long, ItemDto>) httpSession.getAttribute("cart");
                Set<Long> shopIds = new HashSet<Long>();
                for (ItemDto itemDto : map.values()) {
                    shopIds.add(itemDto.getProduct().getShopId());
                }
                //create multi order
                PaymentDto paymentDto = paymentService.getById(dto.getPaymentId());
                if (paymentDto.getMethod() == PaymentMethod.COD) {
                    dto.setStatus(OrderStatus.WAITINGFORACCEPT);
                } else if (paymentDto.getMethod() == PaymentMethod.PAYPAL) {
                    dto.setStatus(OrderStatus.NOTPAID);
                }
                String param = "?orders=";
                for (Long shopId : shopIds) {
                    orderService.add(dto);
                    param += dto.getId() + "&";
                    for (ItemDto itemDto : map.values()) {
                        if (itemDto.getProduct().getShopId() == shopId) {
                            itemDto.setOrderId(dto.getId());
                            itemService.add(itemDto);
                        }
                    }
                }
                param = param.substring(0, param.length() - 1);
                httpSession.removeAttribute("cart");
                if (paymentDto.getMethod() == PaymentMethod.PAYPAL) {
                    double price = 0D;
                    for (ItemDto itemDto : map.values()) {
                        price += itemDto.getUnitPrice();
                    }
                    price = (double) Math.round(price * 0.000043 * 100) / 100;
                    param += "&price=" + price;
                    try {
                        Payment payment = paypalService.createPayment(
                                new Float(price).floatValue(),
                                "USD",
                                PaypalPaymentMethod.paypal,
                                PaypalPaymentIntent.sale,
                                "pay for product from E-Shopper",
                                Constants.baseUrl + "/customer/cancel-paypal",
                                Constants.baseUrl + "/customer/success-paypal" + param);
                        for (Links links : payment.getLinks()) {
                            if (links.getRel().equals("approval_url")) {
                                return "redirect:" + links.getHref();
                            }
                        }
                    } catch (PayPalRESTException e) {
                        e.printStackTrace();
                        return "redirect:/error";
                    }
                } else {
                    return "redirect:/customer/order/success" + param;
                }
            }
        }
        throw new InternalServerException("Phiên đăng nhập hết hạn");
    }

    @GetMapping("/cancel-paypal")
    public String cancelPay() {
        return "redirect:/customer/order/not-paid";
    }


    @GetMapping("/order/pay-again")
    public String payAgain(@RequestParam Long orderId) {
        ViewOrderDto orderDto = orderService.getById(orderId);
        String param = "?orders=" + orderId;
        double price = 0D;
        for (ViewItemDto itemDto : orderDto.getItems()) {
            price += itemDto.getUnitPrice();
        }
        price = (double) Math.round(price * 0.000043 * 100) / 100;
        param += "&price=" + price;
        try {
            Payment payment = paypalService.createPayment(
                    new Float(price).floatValue(),
                    "USD",
                    PaypalPaymentMethod.paypal,
                    PaypalPaymentIntent.sale,
                    "pay again for product from E-Shopper",
                    Constants.baseUrl + "/customer/cancel-paypal",
                    Constants.baseUrl + "/customer/success-paypal" + param);
            for (Links links : payment.getLinks()) {
                if (links.getRel().equals("approval_url")) {
                    return "redirect:" + links.getHref();
                }
            }
        } catch (PayPalRESTException e) {
            e.printStackTrace();
            return "redirect:/error";
        }
        return "redirect:/error";
    }

    @GetMapping("/success-paypal")
    public String createPaymentHistory(@RequestParam(name = "paymentId") String paymentId,
                                       @RequestParam(name = "token") String token,
                                       @RequestParam(name = "PayerID") String payerId,
                                       @RequestParam List<Long> orders,
                                       @RequestParam Double price) throws InternalServerException {

        try {
            Payment payment = paypalService.executePayment(paymentId, payerId);
            if (payment.getState().equals("approved")) {
                UserPrincipal currentUser = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication()
                        .getPrincipal();
                //change order status
                for (Long orderId : orders) {
                    orderService.updateStatus(orderId, OrderStatus.WAITINGFORACCEPT.getValue(), "PAID");
                    //save transaction
                    TransactionDto transactionDto = new TransactionDto();
                    transactionDto.setOrderId(orderId);
                    transactionDto.setPaymentId(paymentId);
                    transactionDto.setPaymentMoney(price);
                    transactionService.add(transactionDto);
                }
                String param = "?orders=";
                for (Long order : orders) {
                    param += order + "&";
                }
                param = param.substring(0, param.length() - 1);
                return "redirect:/customer/order/success" + param;
            }
        } catch (PayPalRESTException e) {
            e.printStackTrace();
        }
        return "redirect:/error";
    }

    @GetMapping("/order/success")
    public String orderSuccess(@RequestParam List<Long> orders) {
        if (orders.size() < 0) {
            return "redirect:/error";
        }
        //check and get order
        return "customer/order/success";
    }

    @GetMapping("/order/not-paid")
    public String orderNotPaid(@RequestParam List<Long> orders) {
        if (orders.size() < 0) {
            return "redirect:/error";
        }
        //check and get order
        return "customer/order/success";
    }

    @GetMapping("/order/view/{id}")
    public String orderDetail(Model model, @PathVariable Long id) {
        ViewOrderDto dto = orderService.getById(id);
        model.addAttribute("order", dto);
        return getViewName(model, "customer/order/order-detail");
    }

}
