package domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class MenuExtended extends Menu {

    private List<MenuExtended> hijos;
    private List<PermisoAuth0> permisos;

    public List<MenuExtended> getHijos() {
        return hijos;
    }

    public void setHijos(List<MenuExtended> hijos) {
        this.hijos = hijos;
    }

    public List<PermisoAuth0> getPermisos() {
        return permisos;
    }

    public void setPermisos(List<PermisoAuth0> permisos) {
        this.permisos = permisos;
    }
}
