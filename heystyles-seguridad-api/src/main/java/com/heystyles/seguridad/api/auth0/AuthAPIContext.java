package com.heystyles.seguridad.api.auth0;

import com.auth0.client.mgmt.ManagementAPI;
import com.heystyles.seguridad.api.config.Auth0Properties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class AuthAPIContext {

    public static final String CONNECTION_AUTH0 = "Username-Password-Authentication";

    @Autowired
    private Auth0Properties properties;

    @Autowired
    @Qualifier("authorizationAPIToken")
    private TokenHelper authorizationAPIToken;

    @Autowired
    @Qualifier("managementAPIToken")
    private TokenHelper managementAPIToken;

    public ManagementAPI newManagementAPI() {
        return new ManagementAPI(properties.getDomain(), managementAPIToken.getAccessToken());
    }

}
