package com.heystyles.seguridad.api.service;

import domain.Login;
import domain.SessionToken;

public interface AuthService {

    SessionToken login(Login login);

    void resetPassword(String correo);
}
