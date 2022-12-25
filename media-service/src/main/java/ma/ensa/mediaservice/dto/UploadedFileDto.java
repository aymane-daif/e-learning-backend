package ma.ensa.mediaservice.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class UploadedFileDto {
    private Long id;
    private String path;
    private String sharedPath;
    private Long courseId;
}
