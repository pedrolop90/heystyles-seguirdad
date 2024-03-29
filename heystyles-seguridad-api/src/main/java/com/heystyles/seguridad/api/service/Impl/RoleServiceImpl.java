package com.heystyles.seguridad.api.service.Impl;

import com.heystyles.seguridad.api.auth0.AuthorizationApi;
import com.heystyles.seguridad.api.exception.Auth0AssignmentException;
import com.heystyles.seguridad.api.service.RoleService;
import com.heystyles.seguridad.api.service.UserService;
import domain.EstadoUser;
import domain.RolAuth0;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RoleServiceImpl implements RoleService {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthorizationApi authorizationApiImpl;

    @Override
    public void assignRoleToUser(Long roleId, Long userAuth0Id) {
        try {
            authorizationApiImpl.assignRoleToUser(userAuth0Id, roleId);
        }
        catch (Exception e) {
            throw new Auth0AssignmentException("Error asignando el rol al usuario.");
        }
    }

    @Override
    public void updateRolToUser(Long userId, Long rolLastId, Long rolNewId) {
        removeRolFromUser(userId, rolLastId);
        assignRoleToUser(rolNewId, userId);
    }

    private void removeRolFromUser(Long userId, Long rolId) {
        try {
            authorizationApiImpl.deleteRoleToUser(userId, rolId);
        }
        catch (Exception e) {
            throw new Auth0AssignmentException("Error eliminando rol del usuario.");
        }
    }

    private boolean deleteUserLengthRoles(Long userId) {
        try {
            if (authorizationApiImpl.findRolByUserId(userId).size() == 0) {
                userService.deleteAuth0User(userId);
                return true;
            }
        }
        catch (Exception e) {
            throw new Auth0AssignmentException("Error eliminando el usuario.");
        }
        return false;
    }

    @Override
    public EstadoUser removeRolToUser(Long userId, Long rolId) {
        removeRolFromUser(userId, rolId);
        if (deleteUserLengthRoles(userId)) {
            return EstadoUser.USER_ELIMINADO;
        }

        return EstadoUser.REMOVIDO_ROL;
    }

    @Override
    public RolAuth0 insert(RolAuth0 rolAuth0) {
        return authorizationApiImpl.createRol(rolAuth0);
    }

    @Override
    public void update(Long rolId, RolAuth0 rolAuth0) {
        authorizationApiImpl.update(rolId, rolAuth0);
    }

    @Override
    public void delete(Long rolId) {
        authorizationApiImpl.delete(rolId);
    }

}
