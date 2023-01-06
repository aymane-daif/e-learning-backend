package com.eleaning.paymentservice.controllers;

import com.eleaning.paymentservice.Exceptions.CourseDoesntExist;
import com.eleaning.paymentservice.Exceptions.StripeException;
import com.eleaning.paymentservice.Exceptions.UserDoesntExist;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler(UserDoesntExist.class)
    public ResponseEntity<String> userDoenstExist(Exception exception, WebRequest webRequest){

        return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());

    }

    @ExceptionHandler(CourseDoesntExist.class)
    public ResponseEntity<String> CourseDoesntExist(Exception exception, WebRequest webRequest){

        return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());

    }

    @ExceptionHandler(StripeException.class)
    public ResponseEntity<String> StripeException(Exception exception, WebRequest webRequest){

        return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());

    }
}
