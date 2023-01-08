package com.elearning.certification_service.controllers;


import com.elearning.certification_service.dtos.CertificateDto;
import com.elearning.certification_service.exceptions.CertificateDoesntExist;
import com.elearning.certification_service.services.CertificationService;

<<<<<<< HEAD
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
=======
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

>>>>>>> a423fc4393db1660d729a6656b03bb1f4427c60a

@RestController
@RequestMapping(path = "/certifications")
public class CertificationController {

    @Autowired
    private CertificationService certificationService;

<<<<<<< HEAD
    @GetMapping("/hola")
    public String createCertificate(@RequestBody CertificateDto certificateDto){
        certificationService.createCertificate(certificateDto);
        return "hola";
=======
    @GetMapping("/")
    public void createCertificate(@RequestBody CertificateDto certificateDto) {
        certificationService.createCertificate(certificateDto);
>>>>>>> a423fc4393db1660d729a6656b03bb1f4427c60a
    }

    @GetMapping("/{id}")
    public CertificateDto getCertificate(@PathVariable String id) throws CertificateDoesntExist {
        return certificationService.getCertificate(id);
    }

}
