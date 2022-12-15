package ma.ensa.course.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ma.ensa.course.entities.Course;
import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Long> {
    List<Course> findAll();
}
