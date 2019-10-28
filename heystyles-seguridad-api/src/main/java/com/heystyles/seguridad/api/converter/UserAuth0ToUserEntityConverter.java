package com.heystyles.seguridad.api.converter;

import com.heystyles.common.exception.APIExceptions;
import com.heystyles.common.util.MessageKeys;
import com.heystyles.seguridad.api.dao.UserDao;
import com.heystyles.seguridad.api.entity.UserEntity;
import domain.UserAuth0;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.Optional;

import static org.springframework.context.i18n.LocaleContextHolder.getLocale;

@Component
public class UserAuth0ToUserEntityConverter implements Converter<UserAuth0, UserEntity> {

    @Autowired
    private UserDao userDao;

    @Autowired
    private MessageSource messageSource;

    @Override
    public UserEntity convert(UserAuth0 bean) {
        UserEntity entity = null;
        if (bean.getId() == null) {
            entity = new UserEntity();
        }
        else {
            entity = Optional.ofNullable(userDao.findOne(bean.getId()))
                    .orElseThrow(() -> APIExceptions.objetoNoEncontrado(
                            messageSource.getMessage(MessageKeys.NOT_FOUND, null, getLocale())));
        }
        entity.setUsuario(bean.getUsuario());
        entity.setPassword(bean.getPassword());
        entity.setEmail(bean.getEmail());
        return entity;
    }
}
