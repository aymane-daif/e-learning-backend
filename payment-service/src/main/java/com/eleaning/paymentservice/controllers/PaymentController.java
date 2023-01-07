package com.eleaning.paymentservice.controllers;


import com.eleaning.paymentservice.dtos.PaymentDto;
import com.eleaning.paymentservice.dtos.SaleDto;
import com.eleaning.paymentservice.services.PaymentService;
import com.eleaning.paymentservice.stripe.StripeClient;
import com.stripe.model.Charge;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping()
    public List<SaleDto> chargeCard(@RequestBody PaymentDto paymentDto,
                                    HttpServletRequest request) throws Exception {

        String userEmail = request.getHeader("email");
        return paymentService.buyCourse(paymentDto, userEmail);
    }

    @GetMapping("/hola")
    public String hola()
    {
        return "hola";
    }



}
