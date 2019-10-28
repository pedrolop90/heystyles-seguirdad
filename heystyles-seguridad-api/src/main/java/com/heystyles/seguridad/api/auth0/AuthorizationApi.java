package com.heystyles.seguridad.api.auth0;

import com.heystyles.common.service.Service;
import domain.RolAuth0;
import java.util.List;

public interface AuthorizationApi extends Service<RolAuth0, Long> {

    void assignRoleToUser(Long userAuth0Id, Long auth0RoleId);

    void deleteRoleToUser(Long userAuth0Id, Long auth0RoleId);

    List<RolAuth0> findRolByUserId(Long userId);

    RolAuth0 createRol(RolAuth0 rolAuth0);

    List<RolAuth0> getRoles();

}
