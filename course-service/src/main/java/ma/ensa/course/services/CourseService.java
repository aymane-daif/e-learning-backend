package ma.ensa.course.services;

import ma.ensa.course.dtos.CourseDto;
import ma.ensa.course.entities.Course;
import ma.ensa.course.entities.CourseLevel;
import ma.ensa.course.entities.PriceType;
import ma.ensa.course.repositories.CourseRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@Service
public class CourseService {

    private final MediaService mediaService;
    private final CourseRepository courseRepository;

    public CourseService(MediaService mediaService, CourseRepository courseRepository) {
        this.mediaService = mediaService;
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

    public Optional<CourseDto> getCourseById(Long id) {
        return courseRepository.findById(id).map(CourseDto::toDto);
    }

    public CourseDto createCourse(CourseDto courseDto, MultipartFile image){
//        mediaService.uploadCourseSupport(image,);
        Course course = Course.fromDto(courseDto);
        return courseDto;

    }
}
