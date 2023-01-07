package ma.ensa.course.controllers;

import ma.ensa.course.dtos.CourseDto;
import ma.ensa.course.entities.CourseLevel;
import ma.ensa.course.entities.PriceType;
import ma.ensa.course.services.CourseService;
import org.springframework.data.domain.Page;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/courses")
public class CourseController {
    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping
    public Page<CourseDto> getCourses(@RequestParam(defaultValue = "0") int page,
                                      @RequestParam(defaultValue = "7") int size,
                                      @RequestParam(required = false) String keyword) {
        return courseService.getCoursesByKeyword(page, size, keyword);
    }

    @GetMapping(path = "/filter")
    public List<CourseDto> getCoursesByCourseLevelOrPriceType(
                                      @RequestParam(required = false) CourseLevel courseLevel,
                                      @RequestParam(required = false) PriceType priceType) {
        return courseService.getCoursesByCourseLevelAndPriceType(courseLevel, priceType);
    }

    @GetMapping(path = "/{id}")
    public Optional<CourseDto> getCourseById(@PathVariable Long id) {
        return courseService.getCourseById(id);
    }

}