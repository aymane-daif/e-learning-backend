package ma.ensa.course.repositories;

import ma.ensa.course.entities.Instructor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InstructorRepository extends JpaRepository<Instructor, Long> {
    List<Instructor> findAll();
}
