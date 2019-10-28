package com.heystyles.seguridad.api.converter;

import com.heystyles.seguridad.api.entity.RolEntity;
import domain.RolAuth0;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class RolEntityToRolAuth0Converter implements Converter<RolEntity, RolAuth0> {

    @Override
    public RolAuth0 convert(RolEntity entity) {
        RolAuth0 bean = new RolAuth0();
        bean.setId(entity.getId());
        bean.setNombre(entity.getNombre());
        bean.setDescripcion(entity.getDescripcion());
        return bean;
    }
}
