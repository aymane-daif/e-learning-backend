package com.elearning.certification_service.controllers;


import com.elearning.certification_service.dtos.CertificateDto;
import com.elearning.certification_service.exceptions.CertificateDoesntExist;
import com.elearning.certification_service.services.CertificationService;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping(path = "/certifications")
public class CertificationController {

    @Autowired
    private CertificationService certificationService;

    @GetMapping("/hola")
    public String createCertificate(@RequestBody CertificateDto certificateDto){
        certificationService.createCertificate(certificateDto);
        return "hola";
    }

    @GetMapping("/{id}")
    public CertificateDto getCertificate(@PathVariable String id) throws CertificateDoesntExist {
        return certificationService.getCertificate(id);
    }

}
