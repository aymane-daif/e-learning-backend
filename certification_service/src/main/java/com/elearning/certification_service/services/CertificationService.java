package com.elearning.certification_service.services;


import com.elearning.certification_service.dtos.CertificateDto;
import com.elearning.certification_service.entities.Certificate;
import com.elearning.certification_service.exceptions.CertificateDoesntExist;
import com.elearning.certification_service.repositories.CertificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class CertificationService {

    @Autowired
    private CertificationRepository certificationRepository;

    public void createCertificate(CertificateDto certificateDto){
        Certificate certificate  = new Certificate(certificateDto);
        certificate.setIssuedDate(new Date());
        certificationRepository.save(certificate);
    }

    public CertificateDto getCertificate(String id) throws CertificateDoesntExist {
        Optional<Certificate> certificate = certificationRepository.findById(id);
        if(!certificate.isPresent()){
            throw new CertificateDoesntExist();
        }
        return new CertificateDto(certificate.get());
    }
}
