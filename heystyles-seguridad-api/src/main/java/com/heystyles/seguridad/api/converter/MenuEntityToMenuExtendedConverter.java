package com.heystyles.seguridad.api.converter;

import com.heystyles.common.service.ConverterService;
import com.heystyles.seguridad.api.entity.MenuEntity;
import domain.MenuExtended;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class MenuEntityToMenuExtendedConverter implements Converter<MenuEntity, MenuExtended> {

    @Autowired
    private ConverterService converterService;

    @Override
    public MenuExtended convert(MenuEntity entity) {
        MenuExtended menuExtended = new MenuExtended();
        menuExtended.setId(entity.getId());
        menuExtended.setNombre(entity.getNombre());
        menuExtended.setPath(entity.getPath());
        menuExtended.setIcono(entity.getIcono());
        menuExtended.setPosicion(entity.getPosicion());
        menuExtended.setEstado(entity.getEstado());
        menuExtended.setIcono(entity.getIcono());
        menuExtended.setHijos(converterService.convertTo(entity.getHijos(), MenuExtended.class));
        return menuExtended;
    }
}
