package com.heystyles.seguridad.api.service.Impl;

import com.auth0.client.auth.AuthAPI;
import com.auth0.exception.Auth0Exception;
import com.auth0.json.auth.TokenHolder;
import com.heystyles.common.service.ConverterService;
import com.heystyles.seguridad.api.config.Auth0Properties;
import com.heystyles.seguridad.api.exception.Auth0InvalidUserException;
import com.heystyles.seguridad.api.service.AuthService;
import com.heystyles.seguridad.api.util.JwtUtil;
import domain.Login;
import domain.LoginHistory;
import domain.SessionToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private AuthAPI authApi;

    @Autowired
    private Auth0Properties auth0Properties;

    @Autowired
    private ConverterService converterService;

    @Autowired
    private HttpServletRequest request;

    @Override
    public SessionToken login(Login login) {
        try {
            TokenHolder holder = authApi.login(login.getEmail(), login.getPassword())
                    .setAudience(auth0Properties.getAudience())
                    .setScope(auth0Properties.getScope())
                    .execute();

            String user = JwtUtil.getUserClaims(holder.getAccessToken());
            LoginHistory loginHistory = new LoginHistory(user, LocalDateTime.now(), request.getRemoteAddr());
            //loginService.insert(loginHistory);
            return converterService.convertTo(holder, SessionToken.class);
        }
        catch (Auth0Exception e) {
            throw new Auth0InvalidUserException("El usuario o la contraseña no coinciden");
        }
    }
}
