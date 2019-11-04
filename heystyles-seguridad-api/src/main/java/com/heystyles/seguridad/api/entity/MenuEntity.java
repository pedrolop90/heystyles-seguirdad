package com.heystyles.seguridad.api.entity;

import domain.Estado;

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
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "menu")
public class MenuEntity extends com.heystyles.common.types.Entity<Long> {

    public interface Attributes extends com.heystyles.common.types.Entity.Attributes {

    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "path", nullable = false)
    private String path;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_menu_padre")
    private MenuEntity menuPadre;

    @Column(name = "id_icono")
    private String icono;

    @Column(name = "posicion")
    private Long posicion;

    @Column(name = "estado")
    @Enumerated(value = EnumType.STRING)
    private Estado estado;

    @OneToMany(mappedBy = "menuPadre", fetch = FetchType.LAZY)
    @OrderBy("posicion ASC")
    private List<MenuEntity> hijos;

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

    public MenuEntity getMenuPadre() {
        return menuPadre;
    }

    public void setMenuPadre(MenuEntity menuPadre) {
        this.menuPadre = menuPadre;
    }

    public String getIcono() {
        return icono;
    }

    public void setIcono(String icono) {
        this.icono = icono;
    }

    public List<MenuEntity> getHijos() {
        return hijos;
    }

    public void setHijos(List<MenuEntity> hijos) {
        this.hijos = hijos;
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
