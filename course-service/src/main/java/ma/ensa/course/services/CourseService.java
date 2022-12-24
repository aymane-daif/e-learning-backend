package ma.ensa.course.services;

import ma.ensa.course.dtos.CourseDto;
import ma.ensa.course.entities.CourseLevel;
import ma.ensa.course.entities.PriceType;
import ma.ensa.course.repositories.CourseRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class CourseService {
    private final CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public Page<CourseDto> getCoursesByKeyword(int page, int size, String keyword) {
        return courseRepository
                .findByNameContainingIgnoreCase(keyword, PageRequest.of(page, size))
                .map(CourseDto::toDto);
    }

    public Page<CourseDto> getCoursesByCourseLevelAndPriceType(int page, int size, CourseLevel courseLevel, PriceType priceType) {
        if(courseLevel == null) {
            return courseRepository.findByPriceType(priceType, PageRequest.of(page, size))
                    .map(CourseDto::toDto);
        }
        if(priceType == null) {
            return courseRepository.findByCourseLevel(courseLevel, PageRequest.of(page, size))
                    .map(CourseDto::toDto);
        }
        return courseRepository
                .findByCourseLevelAndPriceType(courseLevel, priceType, PageRequest.of(page, size))
                .map(CourseDto::toDto);
    }
}
