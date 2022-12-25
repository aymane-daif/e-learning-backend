package ma.ensa.course.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.ensa.course.dtos.LessonDto;
import ma.ensa.course.dtos.SectionDto;

import javax.persistence.*;
import java.util.Set;
import java.util.stream.Collectors;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
public class Section {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany
    private Set<Lesson> lessons;

    public static Section fromDto(SectionDto sectionDto) {
        return Section.builder()
                .id(sectionDto.getId())
                .name(sectionDto.getName())
                .lessons(getCollectedLessons(sectionDto))
                .build();
    }

    private static Set<Lesson> getCollectedLessons(SectionDto sectionDto) {
        return sectionDto.getLessonsDtos().stream().map(Lesson::fromDto).collect(Collectors.toSet());
    }
}
