package com.elearning.certification_service.entities;


import com.elearning.certification_service.dtos.CertificateDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.util.Date;

@Data
@NoArgsConstructor @AllArgsConstructor
@Entity
public class Certificate {

    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    @Column(columnDefinition = "CHAR(32)")
    @Id
    private String id;

    @Temporal(TemporalType.DATE)
    private Date issuedDate;
    private Long courseId;
    private Long userId;
    private String courseTitle;
    private String userFullname;


    public Certificate(CertificateDto dto){

        this.id = dto.getId();
        this.issuedDate = dto.getIssuedDate();
        this.userId = dto.getUserId();
        this.courseId = dto.getCourseId();
        this.courseTitle = dto.getCourseTitle();
        this.userFullname = dto.getUserFullname();

    }
}
