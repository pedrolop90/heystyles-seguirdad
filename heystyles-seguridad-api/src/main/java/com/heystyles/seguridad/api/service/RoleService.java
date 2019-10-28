package com.heystyles.seguridad.api.service;

import domain.EstadoUser;
import domain.RolAuth0;
import dto.RequestRolAuth0;

public interface RoleService {

    void assignRoleToUser(Long roleId, Long userAuth0Id);

    void updateRolToUser(Long userId, Long rolLastId, Long rolNewId);

    EstadoUser removeRolToUser(Long userId, Long rolId);

    RolAuth0 insert(RequestRolAuth0 requestRolAuth0);

    void update(Long rolId, RequestRolAuth0 request);

    void delete(Long rolId);
}
