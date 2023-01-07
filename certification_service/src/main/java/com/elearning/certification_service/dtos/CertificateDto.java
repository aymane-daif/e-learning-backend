package com.elearning.certification_service.dtos;


import com.elearning.certification_service.entities.Certificate;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor @NoArgsConstructor
public class CertificateDto {

    private String id;
    private Date issuedDate;
    private Long courseId;
    private Long userId;
    private String courseTitle;
    private String userFullname;

    public CertificateDto(Certificate certificate){
        this.id = certificate.getId();
        this.issuedDate = certificate.getIssuedDate();
        this.courseId = certificate.getCourseId();
        this.userId = certificate.getUserId();
        this.courseTitle = certificate.getCourseTitle();
        this.userFullname = certificate.getUserFullname();
    }
}
