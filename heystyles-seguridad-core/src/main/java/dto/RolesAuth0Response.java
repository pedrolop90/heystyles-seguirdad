package dto;

import java.util.List;

public class RolesAuth0Response {

    private List<RolAuth0Extended> roles;

    public List<RolAuth0Extended> getRoles() {
        return roles;
    }

    public void setRoles(List<RolAuth0Extended> roles) {
        this.roles = roles;
    }
}
