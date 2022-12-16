package ma.ensa.userservice.service;

import ma.ensa.userservice.Dto.UserDto;
import ma.ensa.userservice.entity.User;
import ma.ensa.userservice.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public static ModelMapper mapper = new ModelMapper();

    public Optional<List<UserDto>> getAllUser() {
        List<UserDto> userDtos = userRepository.findAll()
                .stream()
                .map(user -> toUserDto(user))
                .collect(Collectors.toList());

        return Optional.of(userDtos);

    }

    private UserDto toUserDto(User user) {
        return mapper.map(user, UserDto.class);
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
    public Optional<UserDto> getUserById(Long user_id) {
        return Optional.of(toUserDto(userRepository.findByUserId(user_id)));
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
