package com.heystyles.seguridad.api.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "rol")
public class RolEntity extends com.heystyles.common.types.Entity<Long> {

    public interface Attributes extends com.heystyles.common.types.Entity.Attributes {
        String ROL_PERMISOS = "rolPermisos";
        String ROL_PERMISOS_PERMISO = ROL_PERMISOS + "." + RolPermisoEntity.Attributes.PERMISO;
        String ROL_PERMISOS_PERMISO_ID = ROL_PERMISOS + "." + RolPermisoEntity.Attributes.PERMISO_ID;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "descripcion")
    private String descripcion;

    @OneToMany(mappedBy = "rol", fetch = FetchType.LAZY)
    private List<RolPermisoEntity> rolPermisos;

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

    public List<RolPermisoEntity> getRolPermisos() {
        return rolPermisos;
    }

    public void setRolPermisos(List<RolPermisoEntity> rolPermisos) {
        this.rolPermisos = rolPermisos;
    }
}
