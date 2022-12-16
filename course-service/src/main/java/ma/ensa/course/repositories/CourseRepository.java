package ma.ensa.course.repositories;

import ma.ensa.course.entities.Course;
import ma.ensa.course.entities.CourseLevel;
import ma.ensa.course.entities.PriceType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface CourseRepository extends PagingAndSortingRepository<Course, Long> {
    Page<Course> findByNameContainingIgnoreCase(String keyword, Pageable pageable);
    Page<Course> findByCourseLevelAndPriceType(CourseLevel courseLevel, PriceType priceType, Pageable pageable);
}
