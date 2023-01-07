package ma.ensa.usercoursesservice.serviceclient;

import ma.ensa.usercoursesservice.dtos.CourseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@FeignClient(name = "course-service")
public interface CourseServiceClient {
    @GetMapping(path = "/courses/{id}")
    Optional<CourseDto> getCourseById(@PathVariable Long id);
}
