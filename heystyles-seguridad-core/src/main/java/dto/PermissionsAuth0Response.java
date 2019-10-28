package dto;

import domain.PermisoAuth0;

import java.util.List;

public class PermissionsAuth0Response {

    private List<PermisoAuth0> permissions;

    public List<PermisoAuth0> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<PermisoAuth0> permissions) {
        this.permissions = permissions;
    }
}
