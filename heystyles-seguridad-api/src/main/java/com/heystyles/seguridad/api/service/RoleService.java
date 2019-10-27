package com.heystyles.seguridad.api.service;

import domain.EstadoUser;
import domain.RolAuth0;
import dto.RequestRolAuth0;

public interface RoleService {

    void assignRoleToUser(String roleId, String userAuth0Id);

    void updateRolToUser(String userId, String rolLastId, String rolNewId);

    EstadoUser removeRolToUser(String userId, String rolId);

    RolAuth0 insert(RequestRolAuth0 requestRolAuth0);

    void update(String rolId, RequestRolAuth0 request);

    void delete(String rolId);
}
