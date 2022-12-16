package ma.ensa.course.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.ensa.course.dtos.InstructorDto;

import javax.persistence.*;
import java.util.List;
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
public class Instructor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @OneToMany(mappedBy = "instructor", cascade = CascadeType.ALL)
    private List<Course> courses;

    public static Instructor fromDto(InstructorDto instructorDto) {
        return Instructor.builder()
                .id(instructorDto.getId())
                .name(instructorDto.getName())
                .build();
    }
}
