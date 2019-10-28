package com.heystyles.seguridad.api.converter;

import com.heystyles.seguridad.api.entity.RolEntity;
import com.heystyles.seguridad.api.entity.UserRolEntity;
import domain.RolAuth0;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class UserRolEntityToRolAuth0Converter implements Converter<UserRolEntity, RolAuth0> {
    @Override
    public RolAuth0 convert(UserRolEntity entity) {
        RolEntity rolEntity = entity.getRol();
        RolAuth0 bean = new RolAuth0();
        bean.setNombre(rolEntity.getNombre());
        bean.setDescripcion(rolEntity.getDescripcion());
        return bean;
    }
}
