package com.heystyles.seguridad.api.service.Impl;

import com.heystyles.common.exception.APIExceptions;
import com.heystyles.common.service.ConverterService;
import com.heystyles.seguridad.api.dao.RolPermisoDao;
import com.heystyles.seguridad.api.dao.UserDao;
import com.heystyles.seguridad.api.entity.UserEntity;
import com.heystyles.seguridad.api.message.MessageKeys;
import com.heystyles.seguridad.api.service.AuthService;
import com.heystyles.seguridad.api.service.MenuService;
import domain.Login;
import domain.SessionToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static org.springframework.context.i18n.LocaleContextHolder.getLocale;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private UserDao userDao;

    @Autowired
    private RolPermisoDao rolPermisoDao;

    @Autowired
    private MenuService menuService;

    @Autowired
    private ConverterService converterService;

    @Override
    public SessionToken login(Login login) {
        UserEntity userEntity = Optional.ofNullable(userDao.findByEmailAndPassword(login.getEmail(), login.getPassword()))
                .orElseThrow(() -> APIExceptions.objetoNoEncontrado(
                        messageSource.getMessage(MessageKeys.USERNAME_PASSWORD_INCORRECTO, null, getLocale())));

        SessionToken sessionToken = new SessionToken();
        sessionToken.setEmail(userEntity.getEmail());
        sessionToken.setMenuExtended(menuService.getMenuByUsuario(userEntity.getId()));
        return sessionToken;
    }

}
