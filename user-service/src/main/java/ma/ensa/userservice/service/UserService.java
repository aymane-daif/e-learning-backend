package ma.ensa.userservice.service;

import ma.ensa.userservice.Dto.UserDto;
import ma.ensa.userservice.entity.User;
import ma.ensa.userservice.exception.EmailAlreadyUsed;
import ma.ensa.userservice.exception.KeycloakException;
import ma.ensa.userservice.exception.NickNameALreadyUsed;
import ma.ensa.userservice.exception.UserDoesntExist;
import ma.ensa.userservice.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private KeycloakService keycloakService;

    public static ModelMapper mapper = new ModelMapper();

    public List<UserDto> getAllUser() {

        List<UserDto> userDtos = userRepository.findAll()
                .stream().map(user -> toUserDto(user)).collect(Collectors.toList());
        return userDtos;

    }

    private UserDto toUserDto(User user) {
        return mapper.map(user, UserDto.class);
    }

    public Optional<Long> createNewUser(UserDto userDto) throws NickNameALreadyUsed, EmailAlreadyUsed, KeycloakException {
        System.out.println(userDto);
        nickNameAlreadyUsed(userDto.getNickname());
        emailAlreadyUsed(userDto.getEmail());
        keycloakService.createUser(userDto);
        User user = new User(userDto);
        return Optional.of(userRepository.save(user).getUserId());

    }

    public Optional<UserDto> getUserById(Long user_id) {
        return Optional.of(toUserDto(userRepository.findByUserId(user_id)));
    }

    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }

    public Optional<Long> updateUser(String keycid, UserDto userDto) {
        User user = userRepository.findByUserId(userDto.getUserId());
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
        keycloakService.updateUser(keycid, userDto);
        return Optional.of(userRepository.save(user).getUserId());
    }

    public void nickNameAlreadyUsed(String nickName) throws NickNameALreadyUsed {
        if(userRepository.findUserByNickname(nickName).isPresent()){
            throw new NickNameALreadyUsed();
        }
    }

    public void emailAlreadyUsed(String email) throws EmailAlreadyUsed {
        if(userRepository.findUserByEmail(email).isPresent()){
            throw new EmailAlreadyUsed();
        }
    }

    public UserDto getUserByEmail(String email) throws UserDoesntExist {

        User user = userRepository.findUserByEmail(email).orElseThrow(()->new UserDoesntExist());
        return toUserDto(user);

    }

}
