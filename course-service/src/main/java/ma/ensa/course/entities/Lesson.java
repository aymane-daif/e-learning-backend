package ma.ensa.course.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.ensa.course.dtos.LessonDto;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
public class Lesson {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private boolean isDone;

    @Enumerated
    private LessonType lessonType;

    public static Lesson fromDto(LessonDto lessonDto) {
        return Lesson.builder()
                .id(lessonDto.getId())
                .name(lessonDto.getName())
                .isDone(lessonDto.isDone())
                .lessonType(lessonDto.getLessonType())
                .build();
    }
}
