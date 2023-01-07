package ma.ensa.apigatewayservice.filters;

import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.Base64;
import java.util.Map;


@Component("Logging")
public class LoggingGatewayFilterFactory extends AbstractGatewayFilterFactory {
    @Override
    public GatewayFilter apply(Object config) {
        return(exchange, chain) ->{

            try {
                String token = exchange.getRequest().getHeaders().get("Authorization").get(0);
                token  = token.split(" ")[1];

                SignedJWT signedJWT = SignedJWT.parse(token);
                JWTClaimsSet claimsSet= null;
                claimsSet = signedJWT.getJWTClaimsSet();

                String username = (String) claimsSet.getClaims().get("preferred_username");
                String email = (String) claimsSet.getClaims().get("email");
                System.out.println(email);
                exchange.getRequest().mutate()
                        .header("username", username)
                        .header("email", email)
                        .build();

            } catch (Exception e) {
                System.out.println("invalid token");
            }

           return chain.filter(exchange);
        };
    }
}
