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
@Table(name = "USER_ROL")
public class UserRolEntity extends com.heystyles.common.types.Entity<Long> {

    public interface Attributes extends com.heystyles.common.types.Entity.Attributes {
        String USER = "user";
        String USER_ID = USER + "." + UserEntity.Attributes.ID;
        String USER_USERNAME = USER + "." + UserEntity.Attributes.USERNAME;
        String ROL = "rol";
        String ROL_ID = ROL + "." + RolEntity.Attributes.ID;
        String ROL_ROL_PERMISOS = ROL + "." + RolEntity.Attributes.ROL_PERMISOS;
        String ROL_ROL_PERMISOS_ALIAS = RolEntity.Attributes.ROL_PERMISOS;
        String ROL_ROL_PERMISOS_PERMISO = ROL_ROL_PERMISOS_ALIAS + "." + RolPermisoEntity.Attributes.PERMISO;
        String ROL_ROL_PERMISOS_PERMISO_ALIAS = ROL_ROL_PERMISOS_ALIAS + "." + RolPermisoEntity.Attributes.PERMISO;
        String ROL_ROL_PERMISOS_PERMISO_ID = ROL + "." + RolEntity.Attributes.ROL_PERMISOS_PERMISO_ID;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ID_USER", nullable = false)
    private UserEntity user;


    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ID_ROL", nullable = false)
    private RolEntity rol;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public RolEntity getRol() {
        return rol;
    }

    public void setRol(RolEntity rol) {
        this.rol = rol;
    }
}
