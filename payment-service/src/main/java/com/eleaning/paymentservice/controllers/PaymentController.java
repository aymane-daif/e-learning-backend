package com.eleaning.paymentservice.controllers;


import com.eleaning.paymentservice.dtos.PaymentDto;
import com.eleaning.paymentservice.stripe.StripeClient;
import com.stripe.model.Charge;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payment")
public class PaymentController {
    @Autowired
    private StripeClient stripeClient;

    @PostMapping("/charge")
    public Charge chargeCard(@RequestBody PaymentDto paymentDto) throws Exception {
        return this.stripeClient.chargeNewCard(paymentDto.getToken(), paymentDto.getAmount());
    }


}
