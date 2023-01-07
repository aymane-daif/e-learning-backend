package ma.ensa.usercoursesservice.controller;

import lombok.extern.slf4j.Slf4j;
import ma.ensa.usercoursesservice.dtos.CourseDto;
import ma.ensa.usercoursesservice.dtos.SaleDto;
import ma.ensa.usercoursesservice.serviceclient.CourseServiceClient;
import ma.ensa.usercoursesservice.serviceclient.PaymentServiceClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/user-courses")
@Slf4j
public class UserCoursesController {
    @Autowired
    PaymentServiceClient paymentServiceClient;
    @Autowired
    CourseServiceClient courseServiceClient;
    @GetMapping("/{userId}")
    public ResponseEntity<List<CourseDto>> getUserCourses(@PathVariable Long userId) {
        List<SaleDto> saleDtos = paymentServiceClient.getAllSalesOfUsers(userId).getBody();
        List<CourseDto> courseDtos = new ArrayList<>();
        //JUST A TEST
        courseServiceClient.getCourseById(1L);
        courseServiceClient.getCourseById(1L);
        courseServiceClient.getCourseById(1L);
        return ResponseEntity.status(HttpStatus.OK).body(courseDtos);
    }
}
