package ma.ensa.userservice.service;

import ma.ensa.userservice.Dto.UserDto;
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
        return Optional.of(userRepository.save(user).getUserId());
    }
    public Optional<User> getUserById(Long user_id) {
        return Optional.of(userRepository.findByUserId(user_id));
    }

    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }

    public Optional<Long> updateUser(Long userId, UserDto userDto) {
        User user = userRepository.findByUserId(userId);
        if (userDto.getEmail() != null) {
            user.setEmail(userDto.getEmail());
        }
        if (userDto.getNickname() != null) {
            user.setNickname(userDto.getNickname());
        }
        if (userDto.getFirstName() != null) {
            user.setFirstName(userDto.getFirstName());
        }
        if (userDto.getLastName() != null) {
            user.setLastName(userDto.getLastName());
        }
        if (userDto.getPhone() != null) {
            user.setPhone(userDto.getPhone());
        }
        if (userDto.getDateOfBirth() != null) {
            user.setDateOfBirth(userDto.getDateOfBirth());
        }
        if (userDto.getPassword() != null) {
            user.setPassword(userDto.getPassword());
        }
        return Optional.of(userRepository.save(user).getUserId());
    }
}
