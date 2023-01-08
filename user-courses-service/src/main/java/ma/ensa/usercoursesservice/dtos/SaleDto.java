package ma.ensa.usercoursesservice.dtos;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor @NoArgsConstructor
public class SaleDto {
    private Long id;
    private String userId;
    private Long courseId;

    private Date date;
    private Double price;
}

