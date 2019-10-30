package com.heystyles.seguridad.api.entity;

import domain.TipoAccionPermiso;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "PERMISO")
public class PermisoEntity extends com.heystyles.common.types.Entity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "NOMBRE")
    private String nombre;

    @Column(name = "DESCRIPCION")
    private String descripcion;

    @Column(name = "ACCION")
    @Enumerated(value = EnumType.STRING)
    private TipoAccionPermiso accion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_MENU")
    private MenuEntity menu;

    public PermisoEntity() {
    }

    public PermisoEntity(Long permisoId) {
        setId(permisoId);
    }

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

    public MenuEntity getMenu() {
        return menu;
    }

    public void setMenu(MenuEntity menu) {
        this.menu = menu;
    }
}
