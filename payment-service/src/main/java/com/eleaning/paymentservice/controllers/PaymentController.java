package com.eleaning.paymentservice.controllers;

import com.eleaning.paymentservice.dtos.PaymentDto;
import com.eleaning.paymentservice.dtos.SaleDto;
import com.eleaning.paymentservice.services.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/payment")
@Slf4j
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping()
    public List<SaleDto> chargeCard(@RequestBody PaymentDto paymentDto,
                                    HttpServletRequest request) throws Exception {

        String userEmail = request.getHeader("email");
        return paymentService.buyCourse(paymentDto, userEmail);

    }

    @GetMapping ("/sales/{userId}")
    public ResponseEntity<List<SaleDto>> getAllSalesOfUsers(@PathVariable String userId) {
        log.info("getting sales");
        Optional<List<SaleDto>> saleDtos = paymentService.getAllSalesOfUsers(userId);
        if(saleDtos.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(saleDtos.get());
        }
        return ResponseEntity.status(HttpStatus.OK).body(new ArrayList<>());
    }


}
