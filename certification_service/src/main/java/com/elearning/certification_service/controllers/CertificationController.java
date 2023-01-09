package com.elearning.certification_service.controllers;


import com.elearning.certification_service.dtos.CertificateDto;
import com.elearning.certification_service.exceptions.CertificateDoesntExist;
import com.elearning.certification_service.services.CertificationService;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping(path = "/certifications")
public class CertificationController {

    @Autowired
    private CertificationService certificationService;

    @PostMapping
    public String createCertificate(@RequestBody CertificateDto certificateDto) {
        return certificationService.createCertificate(certificateDto);
    }

    @GetMapping("/{id}")
    public CertificateDto getCertificate(@PathVariable String id) throws CertificateDoesntExist {
        return certificationService.getCertificate(id);
    }
    @GetMapping("/user/{userId}")
    ResponseEntity<Integer> getUserCertifacationsNumber(@PathVariable Long userId) {
        return ResponseEntity.status(HttpStatus.OK).body(certificationService.getUserCertifacationsNumber(userId).get());
    }

}
