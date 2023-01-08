package ma.ensa.course.controllers;

import lombok.extern.slf4j.Slf4j;
import ma.ensa.course.dtos.CourseDto;
import ma.ensa.course.entities.CourseLevel;
import ma.ensa.course.entities.PriceType;
import ma.ensa.course.requests.CourseRequest;
import ma.ensa.course.services.CourseService;
import org.springframework.data.domain.Page;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/courses")
@Slf4j
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
        log.info("getting course");
        return courseService.getCourseById(id);
    }
    
    @PostMapping(path = "",
            consumes = {"multipart/form-data"})
    public CourseDto createCourse(@ModelAttribute CourseRequest courseRequest){

        System.out.println("coursenam;"+courseRequest.getName());
        System.out.println("haimage:"+courseRequest.getImage().getOriginalFilename());
//        System.out.println(email);
        return new CourseDto();

    }

}