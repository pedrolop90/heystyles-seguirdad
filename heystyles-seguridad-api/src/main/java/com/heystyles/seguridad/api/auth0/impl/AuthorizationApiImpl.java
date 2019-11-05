package com.heystyles.seguridad.api.auth0.impl;

import com.heystyles.common.exception.APIExceptions;
import com.heystyles.common.service.impl.ServiceImpl;
import com.heystyles.common.util.MessageKeys;
import com.heystyles.seguridad.api.auth0.AuthorizationApi;
import com.heystyles.seguridad.api.dao.UserDao;
import com.heystyles.seguridad.api.dao.UserRolDao;
import com.heystyles.seguridad.api.entity.UserRolEntity;
import com.heystyles.seguridad.api.service.RolPermisoService;
import com.heystyles.seguridad.api.dao.RolDao;
import com.heystyles.seguridad.api.entity.RolEntity;
import domain.RolAuth0;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.springframework.context.i18n.LocaleContextHolder.getLocale;

@Service
public class AuthorizationApiImpl
        extends ServiceImpl<RolAuth0, RolEntity, Long>
        implements AuthorizationApi {

    @Autowired
    private RolDao rolDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private UserRolDao userRolDao;

    @Autowired
    private RolPermisoService rolPermisoService;

    @Autowired
    private MessageSource messageSource;


    @Override
    protected CrudRepository<RolEntity, Long> getDao() {
        return rolDao;
    }

    @Override
    public void assignRoleToUser(Long userId, Long rolId) {
        UserRolEntity userRolEntity = new UserRolEntity();
        userRolEntity.setUser(userDao.findOne(userId));
        userRolEntity.setRol(rolDao.findOne(rolId));
        userRolDao.save(userRolEntity);
    }

    @Override
    public void deleteRoleToUser(Long userId, Long rolId) {
        UserRolEntity userRolEntity = Optional.ofNullable(userRolDao.findByUserIdAndRolId(userId, rolId))
                .orElseThrow(() -> APIExceptions.objetoNoEncontrado(
                        messageSource.getMessage(MessageKeys.NOT_FOUND, null, getLocale())));
        userRolDao.delete(userRolEntity.getId());
    }

    @Override
    public List<RolAuth0> findRolByUserId(Long userId) {
        List<UserRolEntity> dataList = userRolDao.findByUserId(userId);
        if (dataList.size() > 0) {
            return getConverterService().convertTo(dataList, RolAuth0.class);
        }
        return new ArrayList<>();
    }

    @Override
    public RolAuth0 createRol(RolAuth0 rolAuth0) {
        Long id = insert(rolAuth0);
        rolPermisoService.uppsert(id, rolAuth0.getPermisos());
        return findById(id);
    }

    @Override
    public void update(Long id, RolAuth0 rolAuth0) {
        super.update(id, rolAuth0);
        rolPermisoService.uppsert(id, rolAuth0.getPermisos());
    }

    @Override
    public List<RolAuth0> getRoles() {
        return findAll();
    }
}


