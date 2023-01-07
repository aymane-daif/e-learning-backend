package com.eleaning.paymentservice.dtos;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Data
public class PaymentDto {

    private final String token;
    private final Double price;

    private final List<Long> coursesIds = new ArrayList<>();

}
