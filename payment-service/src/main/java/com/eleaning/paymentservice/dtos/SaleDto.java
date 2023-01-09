package com.eleaning.paymentservice.dtos;


import com.eleaning.paymentservice.entities.Sale;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor @NoArgsConstructor
public class SaleDto {

    private Long id;
    private String userId;
    private Long courseId;

    private Date date;
    private Double price;

    public SaleDto(Sale sale){
        this.id = sale.getId();
        this.courseId = sale.getCourseId();
        this.userId = sale.getUserId();
        this.date = sale.getDate();
        this.price = sale.getPrice();
    }
}
