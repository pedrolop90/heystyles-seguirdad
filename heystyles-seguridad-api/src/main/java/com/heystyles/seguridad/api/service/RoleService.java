package com.heystyles.seguridad.api.service;

import domain.EstadoUser;
import domain.RolAuth0;

public interface RoleService {

    void assignRoleToUser(Long roleId, Long userAuth0Id);

    void updateRolToUser(Long userId, Long rolLastId, Long rolNewId);

    EstadoUser removeRolToUser(Long userId, Long rolId);

    RolAuth0 insert(RolAuth0 rolAuth0);

    void update(Long rolId, RolAuth0 rolAuth0);

    void delete(Long rolId);
}
