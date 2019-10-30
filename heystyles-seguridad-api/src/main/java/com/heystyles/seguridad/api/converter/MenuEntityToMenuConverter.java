package com.heystyles.seguridad.api.converter;

import com.heystyles.seguridad.api.entity.MenuEntity;
import domain.Menu;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class MenuEntityToMenuConverter implements Converter<MenuEntity, Menu> {
    @Override
    public Menu convert(MenuEntity entity) {
        Menu menu = new Menu();
        menu.setId(entity.getId());
        menu.setNombre(entity.getNombre());
        menu.setPath(entity.getPath());
        menu.setIcono(entity.getIcono());
        menu.setPosicion(entity.getPosicion());
        menu.setEstado(entity.getEstado());
        menu.setIcono(entity.getIcono());
        return menu;
    }
}
