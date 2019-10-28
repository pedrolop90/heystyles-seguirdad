package com.heystyles.seguridad.api.converter;

import com.heystyles.seguridad.api.entity.UserEntity;
import domain.UserAuth0;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class UserEntityToUserAuth0Converter implements Converter<UserEntity, UserAuth0> {
    @Override
    public UserAuth0 convert(UserEntity entity) {
        UserAuth0 bean = new UserAuth0();
        bean.setId(entity.getId());
        bean.setUsuario(entity.getUsuario());
        bean.setPassword(entity.getPassword());
        bean.setEmail(entity.getEmail());
        return bean;
    }
}
