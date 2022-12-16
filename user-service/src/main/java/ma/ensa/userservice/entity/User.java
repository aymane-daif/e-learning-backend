package ma.ensa.userservice.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    private String firstName;
    private String lastName;
    @Column(unique = true)
    private String nickname;
    @Column(unique = true)
    private String email;
    private String phone;
    @Temporal(TemporalType.DATE)
    private Date dateOfBirth;
    private String password;
    @Enumerated
    private Role role;
}
