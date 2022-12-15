package ma.ensa.userservice.controller;

import lombok.extern.slf4j.Slf4j;
import ma.ensa.userservice.Dto.UserDto;
import ma.ensa.userservice.entity.Role;
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
    public ResponseEntity<List<User>> getAllUsers() {
        Optional<List<User>> users = userService.getAllUser();
        if (users.isPresent()){
            log.info("list of users");
            return ResponseEntity.status(HttpStatus.OK).body(users.get());
        }
        log.info("no user found");
        return ResponseEntity.status(HttpStatus.OK).body(new ArrayList<>());
    }

    @GetMapping("/teachers")
    public ResponseEntity<List<User>> getAllTeachers() {
        Optional<List<User>> users = userService.getAllUserByRole(Role.TEACHER);
        if (users.isPresent()){
            log.info("list of teachers");
            return ResponseEntity.status(HttpStatus.OK).body(users.get());
        }
        log.info("no teachers found");
        return ResponseEntity.status(HttpStatus.OK).body(new ArrayList<>());
    }
    @GetMapping("/students")
    public ResponseEntity<List<User>> getAllStudents() {
        Optional<List<User>> users = userService.getAllUserByRole(Role.STUDENT);
        if (users.isPresent()){
            log.info("list of students");
            return ResponseEntity.status(HttpStatus.OK).body(users.get());
        }
        log.info("no students found");
        return ResponseEntity.status(HttpStatus.OK).body(new ArrayList<>());
    }
    @GetMapping("/{user_id}")
    public ResponseEntity<User> getUserById(@PathVariable Long user_id) {
        Optional<User> user = userService.getUserById(user_id);
        if (user.isPresent()){
            log.info("user is here");
            return ResponseEntity.status(HttpStatus.OK).body(user.get());
        }
        log.info("user not found");
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }
    @PostMapping
    public ResponseEntity<Long> createNewUser(@RequestBody UserDto userDto) {
        Optional<Long> user_id = userService.createNewUser(userDto);
        log.info("user created");
        return ResponseEntity.status(HttpStatus.CREATED).body(user_id.get());
    }
}
