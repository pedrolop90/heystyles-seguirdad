package com.heystyles.seguridad.api.config;

import com.auth0.client.auth.AuthAPI;
import com.heystyles.seguridad.api.auth0.TokenHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Auth0Config {

    @Autowired
    private Auth0Properties properties;

    @Bean
    public AuthAPI authAPI() {
        AuthAPI authAPI = new AuthAPI(
                properties.getDomain(),
                properties.getClientId(),
                properties.getClientSecret());
        authAPI.setLoggingEnabled(true);
        return authAPI;
    }

    @Bean(name = "authorizationAPIToken")
    public TokenHelper authorizationAPIToken(AuthAPI authAPI) {
        //return new TokenHelper(authAPI, properties.getTokenAudience());
        return null;
    }

    @Bean(name = "managementAPIToken")
    public TokenHelper managementAPIToken(AuthAPI authAPI) {
        return new TokenHelper(authAPI, properties.getManagementAudience());
    }
}
