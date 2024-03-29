package ma.ensa.usercoursesservice.serviceclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@FeignClient("certification-service")
public interface CertificationsServiceClient {
    @GetMapping("/certifications/user/{userId}")
    ResponseEntity<Integer> getUserCertifacationsNumber(@PathVariable Long userId);
}