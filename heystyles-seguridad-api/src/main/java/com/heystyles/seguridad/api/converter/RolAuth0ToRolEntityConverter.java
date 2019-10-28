package com.heystyles.seguridad.api.converter;

import com.heystyles.common.exception.APIExceptions;
import com.heystyles.common.util.MessageKeys;
import com.heystyles.seguridad.api.dao.RolDao;
import com.heystyles.seguridad.api.entity.RolEntity;
import domain.RolAuth0;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.Optional;

import static org.springframework.context.i18n.LocaleContextHolder.getLocale;

@Component
public class RolAuth0ToRolEntityConverter implements Converter<RolAuth0, RolEntity> {

    @Autowired
    private RolDao rolDao;

    @Autowired
    private MessageSource messageSource;

    @Override
    public RolEntity convert(RolAuth0 source) {
        RolEntity entity = null;
        if (source.getId() == null) {
            entity = new RolEntity();
        }
        else {
            entity = Optional.ofNullable(rolDao.findOne(source.getId()))
                    .orElseThrow(() -> APIExceptions.objetoNoEncontrado(
                            messageSource.getMessage(MessageKeys.NOT_FOUND, null, getLocale())));
        }
        entity.setNombre(source.getNombre());
        entity.setDescripcion(source.getDescripcion());
        return entity;
    }
}
