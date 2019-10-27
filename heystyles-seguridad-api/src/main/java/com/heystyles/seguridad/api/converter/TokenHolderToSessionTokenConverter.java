package com.heystyles.seguridad.api.converter;

import com.auth0.json.auth.TokenHolder;
import com.heystyles.seguridad.api.config.Auth0Properties;
import com.heystyles.seguridad.api.util.JwtUtil;
import domain.SessionToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class TokenHolderToSessionTokenConverter implements Converter<TokenHolder, SessionToken> {

    @Autowired
    private Auth0Properties auth0Properties;

    @Override
    public SessionToken convert(TokenHolder tokenHolder) {
        SessionToken sessionToken = new SessionToken();
        sessionToken.setToken(tokenHolder.getAccessToken());
        //sessionToken.setGroups(JwtUtil.getGroups(tokenHolder.getAccessToken(), auth0Properties.getAudience()));
        //sessionToken.setPermissions(JwtUtil.getPermissions(tokenHolder.getAccessToken(), auth0Properties.getAudience()));
        //sessionToken.setName(JwtUtil.getName(tokenHolder.getAccessToken(), auth0Properties.getAudience()));
        //sessionToken.setLastLogin(JwtUtil.getLastLogin(tokenHolder.getAccessToken(), auth0Properties.getAudience()));
        return sessionToken;
    }
}
