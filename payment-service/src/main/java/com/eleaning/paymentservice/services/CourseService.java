package com.eleaning.paymentservice.services;

import com.eleaning.paymentservice.dtos.CourseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="course-service")
@Service
public interface CourseService {

    @GetMapping("/courses/{courseId}")
    public CourseDto getCourseById(@PathVariable Long courseId);

}
