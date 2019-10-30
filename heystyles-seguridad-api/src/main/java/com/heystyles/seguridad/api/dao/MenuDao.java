package com.heystyles.seguridad.api.dao;

import com.heystyles.seguridad.api.entity.MenuEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MenuDao
        extends JpaRepository<MenuEntity, Long> {

    MenuEntity findTopByMenuPadre(MenuEntity menuPadre);

}
