package com.heystyles.seguridad.api.util;

import com.auth0.json.auth.TokenHolder;
import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class JwtUtil {

    private static final String GROUPS_CLAIMS = "groups";
    private static final String PERMISSIONS_CLAIMS = "scope";
    private static final String NAME_CLAIMS = "name";
    private static final String LAST_LOGIN_CLAIMS = "previous_login";
    private static final String USER_CLAIM = "sub";

    protected JwtUtil() {
        throw new UnsupportedOperationException(); // prevents calls from subclass
    }

    public static List<String> getGroups(String jwtToken, String audience) {
        DecodedJWT jwt = JWT.decode(jwtToken);
        return jwt.getClaim(audience + '/' + JwtUtil.GROUPS_CLAIMS).asList(String.class);
    }

    public static String getName(String jwtToken, String audience) {
        DecodedJWT jwt = JWT.decode(jwtToken);
        return jwt.getClaim(audience + '/' + JwtUtil.NAME_CLAIMS).as(String.class);
    }

    public static String getLastLogin(String jwtToken, String audience) {
        DecodedJWT jwt = JWT.decode(jwtToken);
        return jwt.getClaim(audience + '/' + JwtUtil.LAST_LOGIN_CLAIMS).as(String.class);
    }

    public static List<String> getPermissions(String jwtToken, String audience) {
        DecodedJWT jwt = JWT.decode(jwtToken);
        String str = Optional.ofNullable(jwt.getClaim(JwtUtil.PERMISSIONS_CLAIMS).asString()).orElse("");
        return Arrays.asList(str.split(" "));
    }

    public static String getUserClaims(String jwtToken) {
        DecodedJWT jwt = JWT.decode(jwtToken);
        return jwt.getClaim(USER_CLAIM).asString();
    }

    public static boolean isExpiredToken(TokenHolder tokenHolder) {
        DecodedJWT jwt = JWT.decode(tokenHolder.getAccessToken());
        return !jwt.getExpiresAt().after(new Date());
    }
}
