package com.heystyles.seguridad.api.service.Impl;

import com.heystyles.common.exception.APIExceptions;
import com.heystyles.common.service.impl.ServiceImpl;
import com.heystyles.common.util.MessageKeys;
import com.heystyles.seguridad.api.dao.PermisoDao;
import com.heystyles.seguridad.api.dao.RolDao;
import com.heystyles.seguridad.api.entity.PermisoEntity;
import com.heystyles.seguridad.api.service.PermisoService;
import domain.PermisoAuth0;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static org.springframework.context.i18n.LocaleContextHolder.getLocale;

@Service
public class PermisoServiceImpl
        extends ServiceImpl<PermisoAuth0, PermisoEntity, Long> implements PermisoService {

    @Autowired
    private PermisoDao permisoDao;

    @Autowired
    private RolDao rolDao;

    @Autowired
    private MessageSource messageSource;

    @Override
    protected CrudRepository<PermisoEntity, Long> getDao() {
        return permisoDao;
    }

    @Override
    public PermisoAuth0 getPermiso(Long permisoId) {
        return Optional.ofNullable(super.findById(permisoId))
                .orElseThrow(() -> APIExceptions.objetoNoEncontrado(
                        messageSource.getMessage(MessageKeys.NOT_FOUND, null, getLocale())));
    }

}
