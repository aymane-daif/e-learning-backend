package ma.ensa.course.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.ensa.course.entities.Lesson;
import ma.ensa.course.entities.LessonType;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class LessonDto {
    private Long id;
    private String name;
    private boolean isDone;
    private LessonType lessonType;

    public static LessonDto toDto(Lesson lesson) {
        return LessonDto.builder()
                .id(lesson.getId())
                .name(lesson.getName())
                .isDone(lesson.isDone())
                .lessonType(lesson.getLessonType())
                .build();
    }
}
