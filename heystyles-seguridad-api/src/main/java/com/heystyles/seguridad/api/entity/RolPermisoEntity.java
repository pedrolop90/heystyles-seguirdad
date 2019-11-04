package com.heystyles.seguridad.api.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "rol_permiso")
public class RolPermisoEntity extends com.heystyles.common.types.Entity<Long> {

    public interface Attributes extends com.heystyles.common.types.Entity.Attributes {
        String PERMISO = "permiso";
        String PERMISO_ID = PERMISO + "." + PermisoEntity.Attributes.ID;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_rol", nullable = false)
    private RolEntity rol;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_permiso", nullable = false)
    private PermisoEntity permiso;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public RolEntity getRol() {
        return rol;
    }

    public void setRol(RolEntity rol) {
        this.rol = rol;
    }

    public PermisoEntity getPermiso() {
        return permiso;
    }

    public void setPermiso(PermisoEntity permiso) {
        this.permiso = permiso;
    }
}
