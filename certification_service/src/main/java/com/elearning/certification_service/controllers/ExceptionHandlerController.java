package com.elearning.certification_service.controllers;


import com.elearning.certification_service.exceptions.CertificateDoesntExist;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler(CertificateDoesntExist.class)
    public ResponseEntity<String> certificateDoesntExist(Exception exception, WebRequest request){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
    }

}
