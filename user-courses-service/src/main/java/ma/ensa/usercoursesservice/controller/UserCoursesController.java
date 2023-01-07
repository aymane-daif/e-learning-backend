package ma.ensa.usercoursesservice.controller;

import lombok.extern.slf4j.Slf4j;
import ma.ensa.usercoursesservice.dtos.CourseDto;
import ma.ensa.usercoursesservice.serviceclient.UserCoursesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/user-courses")
@Slf4j
public class UserCoursesController {
    @Autowired
    private UserCoursesService userCoursesService;

    @GetMapping("/{userId}")
    public ResponseEntity<CompletableFuture<List<Optional<CourseDto>>>> getUserCourses(@PathVariable Long userId) {
        return ResponseEntity.status(HttpStatus.OK).body(userCoursesService.getUserCourses(userId));
    }
}
