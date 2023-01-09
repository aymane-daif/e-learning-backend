package ma.ensa.userservice.controller;

import lombok.extern.slf4j.Slf4j;
import ma.ensa.userservice.Dto.UserDto;
import ma.ensa.userservice.exception.EmailAlreadyUsed;
import ma.ensa.userservice.exception.KeycloakException;
import ma.ensa.userservice.exception.NickNameALreadyUsed;
import ma.ensa.userservice.exception.UserDoesntExist;
import ma.ensa.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
@Slf4j
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers() {
        List<UserDto> users = userService.getAllUser();
        if (users.isEmpty()){
            log.info("no user found");
        }
        else{
            log.info("list of users");
        }
        return ResponseEntity.status(HttpStatus.OK).body(users);

    }
    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Long userId) {
        Optional<UserDto> userDto = userService.getUserById(userId);

        if (userDto.isPresent()){
            log.info("user is here");
            return ResponseEntity.status(HttpStatus.OK).body(userDto.get());
        }
        log.info("user not found");
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }
    @PostMapping
    public ResponseEntity<Long> createNewUser(@RequestBody UserDto userDto) throws
            NickNameALreadyUsed, EmailAlreadyUsed, KeycloakException {

        Optional<Long> userId = userService.createNewUser(userDto);
        log.info("user created");
        return ResponseEntity.status(HttpStatus.CREATED).body(userId.get());

    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> DeleteUser(@PathVariable Long userId) {
        userService.deleteUser(userId);
        log.info("user deleted");
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(null);
    }

    @PatchMapping("/{keycloakId}")
    public ResponseEntity<Long> updateUser(@PathVariable String keycloakId, @RequestBody UserDto userDto) {
        Optional<Long> userId = userService.updateUser(keycloakId, userDto);
        log.info("user updated");
        return ResponseEntity.status(HttpStatus.OK).body(userId.get());
    }

    @GetMapping("/email/{userEmail}")
    public ResponseEntity<UserDto> checkUserExistenceByEmail(@PathVariable String userEmail)
            throws UserDoesntExist {
        System.out.println(userEmail);
        UserDto user = userService.getUserByEmail(userEmail);

        return ResponseEntity.status(HttpStatus.OK).body(user);

    }

}
