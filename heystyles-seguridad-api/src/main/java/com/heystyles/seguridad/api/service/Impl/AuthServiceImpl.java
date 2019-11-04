package com.heystyles.seguridad.api.service.Impl;

import com.heystyles.common.exception.APIExceptions;
import com.heystyles.common.service.ConverterService;
import com.heystyles.seguridad.api.async.EnviarPasswordInicialUsuarioData;
import com.heystyles.seguridad.api.async.EnviarPasswordInicialUsuarioTask;
import com.heystyles.seguridad.api.dao.RolPermisoDao;
import com.heystyles.seguridad.api.dao.UserDao;
import com.heystyles.seguridad.api.entity.UserEntity;
import com.heystyles.seguridad.api.message.MessageKeys;
import com.heystyles.seguridad.api.service.AuthService;
import com.heystyles.seguridad.api.service.GenerarNumero;
import com.heystyles.seguridad.api.service.MenuService;
import com.heystyles.seguridad.api.service.UserService;
import domain.Login;
import domain.SessionToken;
import domain.UserAuth0;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.MessageSource;
import org.springframework.scheduling.SchedulingTaskExecutor;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static org.springframework.context.i18n.LocaleContextHolder.getLocale;

@Service
public class AuthServiceImpl implements AuthService {

    private static final Long CANTIDAD_DIGITOS = 6L;

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

    @Autowired
    private GenerarNumero generarNumero;

    @Autowired
    private UserService userService;

    @Autowired
    private ApplicationContext context;

    @Autowired
    @Qualifier("threadPoolExecutor")
    private SchedulingTaskExecutor executor;

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

    @Override
    public void resetPassword(String correo) {
        UserEntity userEntity = Optional.ofNullable(userDao.findByEmail(correo))
                .orElseThrow(() -> APIExceptions.objetoNoEncontrado(
                        messageSource.getMessage(MessageKeys.USER_EMAIL_NOT_FOUND,
                                                 new String[]{correo}, getLocale())));
        String newPassword = generarNumero.generar(CANTIDAD_DIGITOS);
        userService.updateUser(userEntity.getId(), converterService.convertTo(userEntity, UserAuth0.class));
        enviarEmail(userEntity.getEmail(), newPassword);
    }

    private void enviarEmail(String email, String password) {
        EnviarPasswordInicialUsuarioData data = new EnviarPasswordInicialUsuarioData();
        data.setEmail(email);
        data.setPassword(password);
        EnviarPasswordInicialUsuarioTask task = context.getBean(EnviarPasswordInicialUsuarioTask.class);
        task.setData(data);
        executor.submit(task);
    }
}
