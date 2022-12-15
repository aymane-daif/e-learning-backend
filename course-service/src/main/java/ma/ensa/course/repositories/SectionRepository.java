package ma.ensa.course.repositories;

import ma.ensa.course.entities.Section;
import org.springframework.data.jpa.repository.JpaRepository;
public interface SectionRepository extends JpaRepository<Section, Long> {
}
