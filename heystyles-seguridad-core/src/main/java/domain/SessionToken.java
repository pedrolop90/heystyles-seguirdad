package domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SessionToken {
    private List<PermisoAuth0> permisos;
    private String nombre;
    private String lastLogin;

    public List<PermisoAuth0> getPermisos() {
        return permisos;
    }

    public void setPermisos(List<PermisoAuth0> permisos) {
        this.permisos = permisos;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(String lastLogin) {
        this.lastLogin = lastLogin;
    }
}
