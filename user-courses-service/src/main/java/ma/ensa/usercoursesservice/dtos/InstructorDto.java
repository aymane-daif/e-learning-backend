package ma.ensa.usercoursesservice.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class InstructorDto {
    private String id;
    private String name;
    private int noCourses;
}
