package ma.ensa.userservice.controller;

import lombok.extern.slf4j.Slf4j;
import ma.ensa.userservice.Dto.UserDto;
import ma.ensa.userservice.entity.User;
import ma.ensa.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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
        Optional<List<UserDto>> users = userService.getAllUser();
        if (users.isPresent()){
            log.info("list of users");
            return ResponseEntity.status(HttpStatus.OK).body(users.get());
        }
        log.info("no user found");
        return ResponseEntity.status(HttpStatus.OK).body(new ArrayList<>());
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
    public ResponseEntity<Long> createNewUser(@RequestBody UserDto userDto) {
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

    @PatchMapping("/{userId}")
    public ResponseEntity<Long> updateUser(@PathVariable Long userId, @RequestBody UserDto userDto) {
        Optional<Long> user_id = userService.updateUser(userId, userDto);
        log.info("user updated");
        return ResponseEntity.status(HttpStatus.OK).body(user_id.get());
    }
}
