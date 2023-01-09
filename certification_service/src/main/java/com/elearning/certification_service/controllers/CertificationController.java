package com.elearning.certification_service.controllers;


import com.elearning.certification_service.dtos.CertificateDto;
import com.elearning.certification_service.exceptions.CertificateDoesntExist;
import com.elearning.certification_service.services.CertificationService;

<<<<<<< HEAD
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
<<<<<<< HEAD
=======
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

>>>>>>> a423fc4393db1660d729a6656b03bb1f4427c60a
=======

>>>>>>> afb696dc2d9e99c3093b2949b011e4d677a5d24c

@RestController
@RequestMapping(path = "/certifications")
public class CertificationController {

    @Autowired
    private CertificationService certificationService;

<<<<<<< HEAD
<<<<<<< HEAD
    @GetMapping("/hola")
    public String createCertificate(@RequestBody CertificateDto certificateDto){
        certificationService.createCertificate(certificateDto);
        return "hola";
=======
=======

>>>>>>> afb696dc2d9e99c3093b2949b011e4d677a5d24c
    @GetMapping("/")
    public void createCertificate(@RequestBody CertificateDto certificateDto) {
        certificationService.createCertificate(certificateDto);
>>>>>>> a423fc4393db1660d729a6656b03bb1f4427c60a
    }

    @GetMapping("/{id}")
    public CertificateDto getCertificate(@PathVariable String id) throws CertificateDoesntExist {
        return certificationService.getCertificate(id);
    }
    @GetMapping("/{userId}")
    ResponseEntity<Integer> getUserCertifacationsNumber(@PathVariable Long userId) {
        return ResponseEntity.status(HttpStatus.OK).body(certificationService.getUserCertifacationsNumber(userId).get());
    }

}
