package com.eleaning.paymentservice.dtos;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
public class PaymentDto {

    private final String token;
    private final Double amount;

}
