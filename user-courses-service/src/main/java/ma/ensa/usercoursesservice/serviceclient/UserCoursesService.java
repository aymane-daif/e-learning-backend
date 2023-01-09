package ma.ensa.usercoursesservice.serviceclient;

import ma.ensa.usercoursesservice.dtos.CourseDto;
import ma.ensa.usercoursesservice.dtos.SaleDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Service
public class UserCoursesService {
    @Autowired
    PaymentServiceClient paymentServiceClient;
    @Autowired
    CourseServiceClient courseServiceClient;

    @Async
    public CompletableFuture<List<Optional<CourseDto>>> getUserCourses(Long userId) {
        List<SaleDto> saleDtos = paymentServiceClient.getAllSalesOfUsers(userId).getBody();
        List<Optional<CourseDto>> courseDtos = new ArrayList<>();
        if(saleDtos != null) {
            List<Long> courseDtosIds = saleDtos
                    .stream().
                    map(SaleDto::getCourseId).toList();

            courseDtos = courseDtosIds.stream()
                    .map(courseDtosId -> courseServiceClient.getCourseById(courseDtosId))
                    .toList();

        }
        return CompletableFuture.completedFuture(courseDtos);
    }
}
