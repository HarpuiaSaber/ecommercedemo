package com.toan.ecommercedemo.apis;

import com.toan.ecommercedemo.model.dto.PaymentDto;
import com.toan.ecommercedemo.services.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "", maxAge = -1)
public class PaymentApi {

    @Autowired
    PaymentService paymentService;

    @GetMapping("/payment/getAll")
    @ResponseBody
    public List<PaymentDto> getAll() {
        return paymentService.getAll();
    }
}

