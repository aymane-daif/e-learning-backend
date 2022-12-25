package ma.ensa.course.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.ensa.course.dtos.CourseDto;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;
import java.util.stream.Collectors;

@NoArgsConstructor @AllArgsConstructor @Data @Builder
@Entity
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private Double price;
    // TODO: grab this from the cloud
    private String image;
    private Integer noStudents;
    private Date lastUpdated;

    @Enumerated
    private CourseLevel courseLevel;
    @Enumerated
    private PriceType priceType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "instructor_id")
    private Instructor instructor;

    @OneToMany
    private Set<Section> sections;

    public static Course fromDto(CourseDto courseDto) {
        return Course.builder()
                .id(courseDto.getId())
                .name(courseDto.getName())
                .description(courseDto.getDescription())
                .price(courseDto.getPrice())
                .image(courseDto.getImage())
                .noStudents(courseDto.getNoStudents())
                .lastUpdated(courseDto.getLastUpdated())
                .courseLevel(courseDto.getCourseLevel())
                .priceType(courseDto.getPriceType())
                .instructor(Instructor.fromDto(courseDto.getInstructorDto()))
                .sections(getCollectedSections(courseDto))
                .build();
    }

    private static Set<Section> getCollectedSections(CourseDto courseDto) {
        return courseDto.getSectionDtos().stream().map(Section::fromDto).collect(Collectors.toSet());
    }
}
