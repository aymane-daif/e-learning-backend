package ma.ensa.userservice.service;

import ma.ensa.userservice.Dto.UserDto;
import ma.ensa.userservice.entity.Role;
import ma.ensa.userservice.entity.User;
import ma.ensa.userservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public Optional<List<User>> getAllUser() {
        return Optional.of(userRepository.findAll());
    }
    public Optional<List<User>> getAllUserByRole(Role role) {
        return Optional.of(userRepository.findAllByRole(role));
    }
    public Optional<Long> createNewUser(UserDto userDto) {
        User user = new User();
        user.setEmail(userDto.getEmail());
        user.setNickname(userDto.getNickname());
        user.setRole(userDto.getRole());
        user.setPhone(userDto.getPhone());
        user.setDateOfBirth(userDto.getDateOfBirth());
        user.setPassword(userDto.getPassword());
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setStudents(userDto.getStudents());
        user.setTeachers(userDto.getTeachers());
        return Optional.of(userRepository.save(user).getUserId());
    }
    public Optional<User> getUserById(Long user_id) {
        return userRepository.findByUserId(user_id);
    }
}
