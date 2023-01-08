package ma.ensa.usercoursesservice.serviceclient;

import ma.ensa.usercoursesservice.dtos.SaleDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.List;

@FeignClient(name = "payment-service")
public interface PaymentServiceClient {
    @GetMapping ("/payment/sales/{userId}")
    ResponseEntity<List<SaleDto>> getAllSalesOfUsers(@PathVariable String userId);
}
