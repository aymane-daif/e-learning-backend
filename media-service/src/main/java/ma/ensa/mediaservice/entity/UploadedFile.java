package ma.ensa.mediaservice.entity;

import lombok.*;

import javax.persistence.*;


@Entity
@Getter
@Builder
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UploadedFile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String path;
    private String sharedPath;
    private Long course_id;

}
