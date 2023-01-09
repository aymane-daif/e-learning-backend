package ma.ensa.userservice.service;

import lombok.extern.slf4j.Slf4j;
import ma.ensa.userservice.Dto.UserDto;
import ma.ensa.userservice.entity.Role;
import ma.ensa.userservice.exception.KeycloakException;
import ma.ensa.userservice.keycloak.KeycloakConfig;
import ma.ensa.userservice.keycloak.KeycloakUtils;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.representations.idm.RoleRepresentation;
import org.springframework.stereotype.Service;

import javax.ws.rs.core.Response;
import java.util.Collections;

@Service
@Slf4j
public class KeycloakService {

    public void createUser(UserDto userDto) throws KeycloakException {
        try {
            Response response = KeycloakConfig
                    .getUsersResource()
                    .create(KeycloakUtils.createUserRepresentation(userDto));
            if (response.getStatus() != 201) {
                log.error("there is a keycloak problem");
                throw new KeycloakException();
            } else {
                addRolesToKeycloakUser(response, userDto.getRole());
            }
        } catch (Exception exception) {
            log.error(exception.getMessage());
            throw new KeycloakException(exception.getMessage());
        }
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
