package ma.ensa.course.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.ensa.course.entities.Instructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class InstructorDto {
    private Long id;
    private String name;
    private int noCourses;

    public static InstructorDto toDto(Instructor instructor) {
        return InstructorDto.builder()
                .id(instructor.getId())
                .name(instructor.getName())
                .noCourses(instructor.getCourses().size())
                .build();
    }
}
