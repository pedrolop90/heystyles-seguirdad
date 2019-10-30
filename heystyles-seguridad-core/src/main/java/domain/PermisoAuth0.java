package domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.heystyles.common.types.DomainBean;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PermisoAuth0 extends DomainBean<Long> {

    private Long id;
    private String nombre;
    private String descripcion;
    private TipoAccionPermiso accion;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public TipoAccionPermiso getAccion() {
        return accion;
    }

    public void setAccion(TipoAccionPermiso accion) {
        this.accion = accion;
    }
}
