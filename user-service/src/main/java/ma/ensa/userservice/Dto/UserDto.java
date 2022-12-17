package ma.ensa.userservice.Dto;

import lombok.*;
import ma.ensa.userservice.entity.Role;


import java.util.Date;


@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private Long userId;
    private String firstName;
    private String lastName;
    private String nickname;
    private String email;
    private String phone;
    private Date dateOfBirth;
    private String password;
    private Role role;
}
