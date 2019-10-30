package com.heystyles.seguridad.api.converter;

import com.heystyles.common.service.ConverterService;
import com.heystyles.seguridad.api.entity.PermisoEntity;
import domain.Menu;
import domain.PermisoAuth0Extended;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class PermisoEntityToPermisoAuth0Extended
        implements Converter<PermisoEntity, PermisoAuth0Extended> {

    @Autowired
    private ConverterService converterService;

    @Override
    public PermisoAuth0Extended convert(PermisoEntity entity) {
        PermisoAuth0Extended bean = new PermisoAuth0Extended();
        bean.setId(entity.getId());
        bean.setNombre(entity.getNombre());
        bean.setDescripcion(entity.getDescripcion());
        bean.setAccion(entity.getAccion());
        bean.setMenu(converterService.convertTo(entity.getMenu(), Menu.class));
        return bean;
    }
}
