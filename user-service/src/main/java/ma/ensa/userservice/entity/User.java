package ma.ensa.userservice.entity;

import lombok.*;
import ma.ensa.userservice.Dto.UserDto;

import javax.persistence.*;
import java.util.Date;

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
