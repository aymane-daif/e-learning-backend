package ma.ensa.userservice.entity;

import lombok.*;
import ma.ensa.userservice.Dto.UserDto;
import ma.ensa.userservice.entity.Role;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    @Enumerated(EnumType.STRING)
    private Role role;

    public User(UserDto userDto){
        userId = userDto.getUserId();
        firstName = userDto.getFirstName();
        lastName = userDto.getLastName();
        nickname =  userDto.getNickname();
        email = userDto.getEmail();
        phone = userDto.getPhone();
        dateOfBirth = userDto.getDateOfBirth();
        role = userDto.getRole();
    }

}
