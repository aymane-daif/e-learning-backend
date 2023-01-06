package com.eleaning.paymentservice.dtos;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor @NoArgsConstructor
public class CourseDto {

    private Long id;

    private Double price;
}
