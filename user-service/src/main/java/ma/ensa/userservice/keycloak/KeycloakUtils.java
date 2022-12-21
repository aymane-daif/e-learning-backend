package ma.ensa.userservice.keycloak;

import ma.ensa.userservice.Dto.UserDto;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.RoleRepresentation;
import org.keycloak.representations.idm.UserRepresentation;

import javax.ws.rs.core.Response;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class KeycloakUtils {

    public static UserRepresentation createUserRepresentation (UserDto user) {

        UserRepresentation userRepresentation = new UserRepresentation();

        userRepresentation.setUsername(user.getNickname());
        userRepresentation.setEmail(user.getEmail());
        userRepresentation.setFirstName(user.getFirstName());
        userRepresentation.setLastName(user.getLastName());
        userRepresentation.setCredentials(
                Collections.
                singletonList(createPasswordCredentials(user.getPassword())));
        userRepresentation.setEnabled(true);
        userRepresentation.setEmailVerified(true);

        return userRepresentation;

    }

    public static CredentialRepresentation createPasswordCredentials(String password) {
        CredentialRepresentation passwordCredentials = new CredentialRepresentation();
        passwordCredentials.setTemporary(false);
        passwordCredentials.setType(CredentialRepresentation.PASSWORD);
        passwordCredentials.setValue(password);
        return passwordCredentials;
    }

}
