package ma.ensa.course.services;

import ma.ensa.course.dtos.CourseDto;
import ma.ensa.course.entities.CourseLevel;
import ma.ensa.course.entities.PriceType;
import ma.ensa.course.repositories.CourseRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public List<CourseDto> getCoursesByCourseLevelAndPriceType(CourseLevel courseLevel, PriceType priceType) {
        if(courseLevel == null) {
            return courseRepository.findByPriceType(priceType)
                    .stream()
                    .map(CourseDto::toDto)
                    .toList();
        }
        if(priceType == null) {
            return courseRepository.findByCourseLevel(courseLevel)
                    .stream()
                    .map(CourseDto::toDto)
                    .toList();
        }
        return courseRepository
                .findByCourseLevelAndPriceType(courseLevel, priceType)
                .stream()
                .map(CourseDto::toDto)
                .toList();
    }
}
