package com.elearning.certification_service.repositories;

import com.elearning.certification_service.entities.Certificate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CertificationRepository extends JpaRepository<Certificate, String> {
    int countAllByUserId(Long userId);
}
