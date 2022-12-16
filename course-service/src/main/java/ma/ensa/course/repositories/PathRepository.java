package ma.ensa.course.repositories;

import ma.ensa.course.entities.Path;
import org.springframework.data.jpa.repository.JpaRepository;
public interface PathRepository extends JpaRepository<Path, Long> {
}
