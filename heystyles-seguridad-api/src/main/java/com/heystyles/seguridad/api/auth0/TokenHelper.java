package com.heystyles.seguridad.api.auth0;

import com.auth0.client.auth.AuthAPI;
import com.auth0.exception.Auth0Exception;
import com.auth0.json.auth.TokenHolder;
import com.heystyles.seguridad.api.exception.Auth0RuntimeException;
import com.heystyles.seguridad.api.util.JwtUtil;

public class TokenHelper {

    private AuthAPI authApi;
    private String audience;

    public TokenHelper(AuthAPI authAPI, String audience) {
        this.authApi = authAPI;
        this.audience = audience;
    }

    private TokenHolder token;

    private TokenHolder getToken() {
        if (token == null || JwtUtil.isExpiredToken(token)) {
            try {
                token = authApi.requestToken(audience).execute();
            }
            catch (Auth0Exception e) {
                throw new Auth0RuntimeException(e);
            }
        }
        return token;
    }

    public String getAccessToken() {
        return getToken().getAccessToken();
    }

}
