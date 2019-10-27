package com.heystyles.seguridad.api.auth0;

import java.util.List;

public class PermissionsAuth0Response {

    private List<PermissionAuth0> permissions;

    public List<PermissionAuth0> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<PermissionAuth0> permissions) {
        this.permissions = permissions;
    }
}
