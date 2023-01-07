package ma.ensa.usercoursesservice.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class SectionDto {
    private Long id;
    private String name;
    private Set<LessonDto> lessonsDtos;
}
