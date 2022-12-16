package ma.ensa.userservice.Dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import ma.ensa.userservice.entity.Role;
import ma.ensa.userservice.entity.User;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
