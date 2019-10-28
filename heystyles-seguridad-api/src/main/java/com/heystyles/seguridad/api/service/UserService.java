package com.heystyles.seguridad.api.service;

import com.heystyles.common.service.Service;
import domain.UserAuth0;

public interface UserService extends Service<UserAuth0, Long> {

    Long createUserAuth0(UserAuth0 userAuth0);

    void deleteAuth0User(Long idUsuario);

    void updateUser(Long auth0Id, UserAuth0 user);

}
