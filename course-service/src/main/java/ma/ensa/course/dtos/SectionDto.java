package ma.ensa.course.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.ensa.course.entities.Lesson;
import ma.ensa.course.entities.Section;

import java.util.Set;
import java.util.stream.Collectors;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class SectionDto {
    private Long id;
    private String name;
    private Set<LessonDto> lessonsDtos;

    public static SectionDto toDto(Section section) {
        return SectionDto.builder()
                .id(section.getId())
                .name(section.getName())
                .lessonsDtos(getCollectedLessonDtos(section))
                .build();
    }

    private static Set<LessonDto> getCollectedLessonDtos(Section section) {
        return section.getLessons().stream().map(LessonDto::toDto).collect(Collectors.toSet());
    }
}
