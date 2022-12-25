package ma.ensa.course.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.ensa.course.entities.Course;
import ma.ensa.course.entities.CourseLevel;
import ma.ensa.course.entities.PriceType;

import java.util.Date;
import java.util.Set;
import java.util.stream.Collectors;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class CourseDto {
    private Long id;
    private String name;
    private String description;
    private Double price;
    private String image;
    private Integer noStudents;
    private Date lastUpdated;
    private CourseLevel courseLevel;
    private PriceType priceType;
    private InstructorDto instructorDto;
    private Set<SectionDto> sectionDtos;

    public static CourseDto toDto(Course course) {
        return CourseDto.builder()
                .id(course.getId())
                .name(course.getName())
                .description(course.getDescription())
                .price(course.getPrice())
                .image(course.getImage())
                .noStudents(course.getNoStudents())
                .lastUpdated(course.getLastUpdated())
                .courseLevel(course.getCourseLevel())
                .priceType(course.getPriceType())
                .instructorDto(InstructorDto.toDto(course.getInstructor()))
                .sectionDtos(getCollectedSectionDtos(course))
                .build();
    }

    private static Set<SectionDto> getCollectedSectionDtos(Course course) {
        return course.getSections()
                .stream()
                .map(SectionDto::toDto)
                .collect(Collectors.toSet());
    }
}
