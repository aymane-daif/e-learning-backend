package ma.ensa.userservice.keycloak;

import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.admin.client.resource.UsersResource;


public class KeycloakConfig {
    static Keycloak keycloak = null;
    final static String serverUrl = "http://localhost:8181/auth";
    public final static String realm = "e-learning";

    final static String clientId = "user-service";
    final static String clientSecret = "YOUR_CLIENT_SECRET_KEY";
    final static String USERNAME = "myadmin";
    final static String PASSWORD = "myadmin";

    public KeycloakConfig() {
    }

    public static Keycloak getInstance(){

        if(keycloak == null){

            keycloak = KeycloakBuilder.builder()
                    .serverUrl(serverUrl)
                    .realm(realm)
                    .clientId(clientId)
                    .grantType(OAuth2Constants.PASSWORD)
                    .username(USERNAME)
                    .password(PASSWORD)
                    .resteasyClient(new ResteasyClientBuilder()
                            .connectionPoolSize(10)
                            .build())
                    .build();
        }
        return keycloak;
    }
    public static RealmResource getRealmResource(){

        return getInstance().realm(realm);

    }

    public static UsersResource getUsersResource(){

        return getRealmResource().users();

    }
}