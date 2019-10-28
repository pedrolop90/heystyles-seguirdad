package com.heystyles.seguridad.api.service.Impl;

import com.heystyles.common.service.ConverterService;
import com.heystyles.seguridad.api.auth0.AuthorizationApi;
import com.heystyles.seguridad.api.exception.Auth0AssignmentException;
import com.heystyles.seguridad.api.service.RoleService;
import com.heystyles.seguridad.api.service.UserService;
import domain.EstadoUser;
import domain.RolAuth0;
import dto.RequestRolAuth0;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RoleServiceImpl implements RoleService {

    @Autowired
    private ConverterService converterService;

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
    public RolAuth0 insert(RequestRolAuth0 requestRolAuth0) {
        RolAuth0 rolAuth0Create = converterService
                .convertTo(requestRolAuth0, RolAuth0.class);
        return authorizationApiImpl.createRol(rolAuth0Create);
    }

    @Override
    public void update(Long rolId, RequestRolAuth0 requestRolAuth0) {
        authorizationApiImpl.update(rolId, converterService.convertTo(requestRolAuth0, RolAuth0.class));
    }

    @Override
    public void delete(Long rolId) {
        authorizationApiImpl.delete(rolId);
    }

}
