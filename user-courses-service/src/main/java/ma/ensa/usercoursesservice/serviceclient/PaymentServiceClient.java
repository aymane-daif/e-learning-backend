package ma.ensa.usercoursesservice.serviceclient;

import ma.ensa.usercoursesservice.dtos.SaleDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.List;

@FeignClient(name = "payment-serviceclient", url = "localhost:8083/payment")
public interface PaymentServiceClient {
    @GetMapping ("/sales/{userId}")
    ResponseEntity<List<SaleDto>> getAllSalesOfUsers(@PathVariable Long userId);
}
