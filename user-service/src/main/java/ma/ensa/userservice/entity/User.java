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
<<<<<<< HEAD
    private Long id;
=======
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
>>>>>>> afb696dc2d9e99c3093b2949b011e4d677a5d24c
    private String firstName;
    private String lastName;
    @Column(unique = true)
    private String nickname;
    @Column(unique = true)
    private String email;
    private String phone;
    @Temporal(TemporalType.DATE)
    private Date dateOfBirth;
    @Enumerated(EnumType.STRING)
    private Role role;

    public User(UserDto userDto){
        id = userDto.getId();
        firstName = userDto.getFirstName();
        lastName = userDto.getLastName();
        nickname =  userDto.getNickname();
        email = userDto.getEmail();
        phone = userDto.getPhone();
        dateOfBirth = userDto.getDateOfBirth();
        role = userDto.getRole();
    }

}
