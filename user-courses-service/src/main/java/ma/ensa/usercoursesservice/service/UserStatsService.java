package ma.ensa.usercoursesservice.service;

import ma.ensa.usercoursesservice.serviceclient.CertificationsServiceClient;
import ma.ensa.usercoursesservice.serviceclient.PaymentServiceClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserStatsService {

    @Autowired
    private PaymentServiceClient paymentServiceClient;
    @Autowired
    private CertificationsServiceClient certificationsServiceClient;

    public Optional<Integer> getUserCoursesNumber(Long userId) {
        return Optional.of(paymentServiceClient.getAllSalesOfUsers(userId).getBody().size());
    }

    public Optional<Integer> getUserCertificationsNumber(Long userId) {
        return Optional.of(certificationsServiceClient.getUserCertifacationsNumber(userId).getBody());
    }
}
