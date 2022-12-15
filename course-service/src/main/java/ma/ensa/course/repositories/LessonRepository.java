package ma.ensa.course.repositories;

import ma.ensa.course.entities.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;
public interface LessonRepository extends JpaRepository<Lesson, Long> {
}
