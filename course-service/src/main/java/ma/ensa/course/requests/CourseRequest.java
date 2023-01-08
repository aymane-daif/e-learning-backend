package ma.ensa.course.requests;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.ensa.course.entities.CourseLevel;
import ma.ensa.course.entities.PriceType;
import org.springframework.web.multipart.MultipartFile;

@Data
@AllArgsConstructor @NoArgsConstructor
public class CourseRequest {
    private String name;
    private PriceType priceType;
    private Long intructorId;
    private CourseLevel courseLevel;
    private Double price;
    private String description;

    private String email;
    private MultipartFile image;
}
