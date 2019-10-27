package com.heystyles.seguridad.api.service;

import domain.UserAuth0;
import domain.UsuarioAuth0;

public interface UserService {

    String createUserAuth0(UserAuth0 userAuth0);

    void deleteAuth0User(String idUsuario);

    void updateUser(String auth0Id, UsuarioAuth0 user);

}
