package ma.ensa.usercoursesservice.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class LessonDto {
    private Long id;
    private String name;
    private boolean isDone;
    private LessonType lessonType;

}
