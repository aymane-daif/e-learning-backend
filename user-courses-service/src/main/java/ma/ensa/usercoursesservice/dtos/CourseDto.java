package ma.ensa.usercoursesservice.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
}
