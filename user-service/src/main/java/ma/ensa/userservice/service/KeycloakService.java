package ma.ensa.userservice.service;

import lombok.extern.slf4j.Slf4j;
import ma.ensa.userservice.Dto.UserDto;
import ma.ensa.userservice.entity.Role;
import ma.ensa.userservice.entity.User;
import ma.ensa.userservice.exception.KeycloakException;
import ma.ensa.userservice.keycloak.KeycloakConfig;
import ma.ensa.userservice.keycloak.KeycloakUtils;
import ma.ensa.userservice.repository.UserRepository;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.representations.idm.RoleRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.ws.rs.core.Response;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class KeycloakService {
    @Autowired
    private UserRepository userRepository;
    public Optional<String> createUser(UserDto userDto) throws KeycloakException {
        try {
            Response response = KeycloakConfig
                    .getUsersResource()
                    .create(KeycloakUtils.createUserRepresentation(userDto));
            if (response.getStatus() != 201) {
                log.error("there is a keycloak problem");
                throw new KeycloakException();
            } else {
                addRolesToKeycloakUser(response, userDto.getRole());
                for (UserRepresentation userRepresentation: this.getAllUsers()) {
                    if(userRepresentation.getUsername().equals(userDto.getNickname().toLowerCase())) {
                        userDto.setUserId(userRepresentation.getId());
                    }
                }
                if (userDto.getUserId() != null) {
                    this.userRepository.save(UserService.mapper.map(userDto, User.class));
                    return Optional.of(userDto.getUserId());
                }
                else {
                    log.error("user exists");
                    return Optional.of("user already exists");
                }
            }

        } catch (Exception exception) {
            log.error(exception.getMessage());
            throw new KeycloakException(exception.getMessage());
        }
    }
    public void updateUser(String userId, UserDto userDto) {
            UserRepresentation userRepresentation = new UserRepresentation();
            userRepresentation.setFirstName(userDto.getFirstName());
            userRepresentation.setLastName(userDto.getLastName());
            userRepresentation.setEmail(userDto.getEmail());
            userRepresentation.setUsername(userDto.getNickname());
            log.info(userId);
            KeycloakConfig.getUsersResource().get(userId).update(userRepresentation);
    }

    public List<UserRepresentation> getAllUsers() {
        return KeycloakConfig.getUsersResource().list();
    }

    public void addRolesToKeycloakUser(Response response, Role userRole) {
        String path = response.getLocation().getPath();
        String userId = path.substring(path.lastIndexOf("/") + 1);
        System.out.println(userId);
        RealmResource realmResource = KeycloakConfig.getRealmResource();
        RoleRepresentation roleRepresentation = realmResource.roles().get(userRole.name()).toRepresentation();
        realmResource.users().get(userId).roles()
                        .realmLevel().add(Collections.singletonList(roleRepresentation));
    }


}
