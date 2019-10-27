package com.heystyles.seguridad.api.service.Impl;

import com.heystyles.common.service.ConverterService;
import com.heystyles.seguridad.api.auth0.AuthAPIContext;
import com.heystyles.seguridad.api.auth0.AuthorizationAPI;
import com.heystyles.seguridad.api.auth0.PermissionAuth0;
import com.heystyles.seguridad.api.auth0.RolAuth0Create;
import com.heystyles.seguridad.api.config.Auth0Properties;
import com.heystyles.seguridad.api.exception.Auth0AssignmentException;
import com.heystyles.seguridad.api.service.RoleService;
import com.heystyles.seguridad.api.service.UserService;
import domain.EstadoUser;
import domain.RolAuth0;
import dto.RequestRolAuth0;
import dto.RolAuth0Extended;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class RoleServiceImpl implements RoleService {

    @Autowired
    private ConverterService converterService;

    @Autowired
    private AuthAPIContext authAPIContext;

    @Autowired
    private UserService userService;

    @Autowired
    private Cache roleCache;

    @Autowired
    private CachePermissions cachePermissions;

    @Override
    public void assignRoleToUser(String roleId, String userAuth0Id) {
        try {
            AuthorizationAPI authorizationAPI = authAPIContext.newAuthorizationAPI();
            authorizationAPI.assignRoleToUser(userAuth0Id, roleId);
        }
        catch (Exception e) {
            throw new Auth0AssignmentException("Error asignando el rol al usuario.");
        }
    }

    @Override
    public void updateRolToUser(String userId, String rolLastId, String rolNewId) {
        removeRolFromUser(userId, rolLastId);
        assignRoleToUser(rolNewId, userId);
    }

    private void removeRolFromUser(String userId, String rolId) {
        try {
            AuthorizationAPI authorizationAPI = authAPIContext.newAuthorizationAPI();
            authorizationAPI.deleteRoleToUser(userId, rolId);
        }
        catch (Exception e) {
            throw new Auth0AssignmentException("Error eliminando rol del usuario.");
        }
    }

    private boolean deleteUserLengthRoles(String userId) {
        try {
            AuthorizationAPI authorizationAPI = authAPIContext.newAuthorizationAPI();
            if (authorizationAPI.findRolByUserId(userId).size() == 0) {
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
    public EstadoUser removeRolToUser(String userId, String rolId) {
        removeRolFromUser(userId, rolId);
        if (deleteUserLengthRoles(userId)) {
            return EstadoUser.USER_ELIMINADO;
        }

        return EstadoUser.REMOVIDO_ROL;
    }

    @Override
    public RolAuth0 insert(RequestRolAuth0 requestRolAuth0) {
        AuthorizationAPI authorizationAPI = authAPIContext.newAuthorizationAPI();
        RolAuth0Create rolAuth0Create = converterService
                .convertTo(requestRolAuth0, RolAuth0Create.class);
        //rolAuth0Create.setPermissions(cachePermissions.getPermissionsIds());
        rolAuth0Create.setPermissions(new ArrayList<>());
        RolAuth0 response = authorizationAPI.createRol(rolAuth0Create);
        return response;
    }

    @Override
    public void update(String rolId, RequestRolAuth0 requestRolAuth0) {
        AuthorizationAPI authorizationAPI = authAPIContext.newAuthorizationAPI();
        RolAuth0Extended rolAuth0Extended = authorizationAPI
                .getRoles()
                .stream()
                .filter(r -> r.getId().equals(rolId))
                .findFirst()
                .get();
        RolAuth0Create rolAuth0Create = converterService
                .convertTo(rolAuth0Extended, RolAuth0Create.class);
        rolAuth0Create.setName(requestRolAuth0.getName());
        rolAuth0Create.setDescription(requestRolAuth0.getDescription());
        authorizationAPI.updateRol(rolId, rolAuth0Create);
    }

    @Override
    public void delete(String rolId) {
        AuthorizationAPI authorizationAPI = authAPIContext.newAuthorizationAPI();
        authorizationAPI.deleteRol(rolId);
    }

    @Component
    private class CachePermissions {

        @Autowired
        private Auth0Properties auth0Properties;
        private List<String> permissionsIds;

        @PostConstruct
        public void load() {
            AuthorizationAPI authorizationAPI = authAPIContext.newAuthorizationAPI();
            permissionsIds = authorizationAPI.getPermissions()
                    .stream()
                    //.filter(p -> auth0Properties.getNamePermissions().contains(p.getName()))
                    .map(PermissionAuth0::getId)
                    .collect(Collectors.toList());
        }

        public List<String> getPermissionsIds() {
            return permissionsIds;
        }
    }

    @Component
    private class Cache {

        private Map<String, String> roleIdByName;

        @PostConstruct
        public void load() {
            AuthorizationAPI authorizationAPI = authAPIContext.newAuthorizationAPI();
            List<RolAuth0Extended> rolesAuth0 = authorizationAPI.getRoles();
            roleIdByName = rolesAuth0.stream().collect(Collectors.toMap(RolAuth0Extended::getName, RolAuth0Extended::getId));
        }

        String getIdSecurityFromRol(String nameRol) {
            return roleIdByName.get(nameRol);
        }

    }

}
