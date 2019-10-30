package domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.heystyles.common.types.DomainBean;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Menu extends DomainBean<Long> {

    private Long id;
    private String nombre;
    private String path;
    private String icono;
    private Long posicion;
    private Estado estado;

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

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getIcono() {
        return icono;
    }

    public void setIcono(String icono) {
        this.icono = icono;
    }

    public Long getPosicion() {
        return posicion;
    }

    public void setPosicion(Long posicion) {
        this.posicion = posicion;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }
}
