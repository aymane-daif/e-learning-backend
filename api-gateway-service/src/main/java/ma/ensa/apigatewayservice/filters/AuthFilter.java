package ma.ensa.apigatewayservice.filters;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.stereotype.Component;


@Component("Logging")
public class AuthFilter extends AbstractGatewayFilterFactory {
    @Override
    public GatewayFilter apply(Object config) {
        System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaa");
        return null;
    }
}
