package com.heystyles.seguridad.api.converter;

import com.heystyles.seguridad.api.entity.PermisoEntity;
import domain.PermisoAuth0;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class PermisoEntityToPermisoAuth0Converter implements Converter<PermisoEntity, PermisoAuth0> {
    @Override
    public PermisoAuth0 convert(PermisoEntity entity) {
        PermisoAuth0 bean = new PermisoAuth0();
        bean.setId(entity.getId());
        bean.setNombre(entity.getNombre());
        bean.setDescripcion(entity.getDescripcion());
        bean.setAccion(entity.getAccion());
        return bean;
    }
}
