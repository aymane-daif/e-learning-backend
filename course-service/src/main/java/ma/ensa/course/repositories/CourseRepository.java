package ma.ensa.course.repositories;

import ma.ensa.course.entities.Course;
import ma.ensa.course.entities.CourseLevel;
import ma.ensa.course.entities.PriceType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface CourseRepository extends PagingAndSortingRepository<Course, Long> {
    Page<Course> findByNameContainingIgnoreCase(String keyword, Pageable pageable);
    List<Course> findByPriceType(PriceType priceType);
    List<Course> findByCourseLevel(CourseLevel courseLevel);
    List<Course> findByCourseLevelAndPriceType(CourseLevel courseLevel, PriceType priceType);

}
