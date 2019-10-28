package domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.heystyles.common.types.DomainBean;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RolAuth0  extends DomainBean<Long> {

    private Long id;

    private String nombre;

    private String descripcion;

    private List<Long> permisos;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Long> getPermisos() {
        return permisos;
    }

    public void setPermisos(List<Long> permisos) {
        this.permisos = permisos;
    }
}
