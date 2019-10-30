package com.heystyles.seguridad.api.validator;

import com.heystyles.common.validation.ValidationError;
import com.heystyles.common.validation.Validator;
import com.heystyles.common.validation.groups.Insert;
import com.heystyles.common.validation.groups.Update;
import com.heystyles.seguridad.api.dao.UserDao;
import com.heystyles.seguridad.api.entity.UserEntity;
import com.heystyles.seguridad.api.message.MessageKeys;
import domain.UserAuth0;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static org.springframework.context.i18n.LocaleContextHolder.getLocale;

@Component
public class UserAuth0UnicoValidator implements Validator<UserAuth0> {

    @Autowired
    private UserDao userDao;

    @Autowired
    private MessageSource messageSource;

    @Override
    public List<ValidationError> validate(UserAuth0 userAuth0) {
        List<ValidationError> errors = new ArrayList<>();
        UserEntity userEntityEmail = userDao.findByEmail(userAuth0.getEmail());
        if (userEntityEmail != null && !Objects.equals(userEntityEmail.getId(), userAuth0.getId())) {
            errors.add(new ValidationError("Email",
                                           messageSource.getMessage(MessageKeys.USER_EMAIL_DUPLICADO, null, getLocale())));
        }
        UserEntity userEntityUsername = userDao.findByEmail(userAuth0.getEmail());
        if (userEntityUsername != null && !Objects.equals(userEntityUsername.getId(), userAuth0.getId())) {
            errors.add(new ValidationError("Username",
                                           messageSource.getMessage(MessageKeys.USER_USUARIO_DUPLICADO, null, getLocale())));
        }

        return errors;
    }

    @Override
    public List<Class<?>> getScopes() {
        return Arrays.asList(Insert.class, Update.class);
    }
}
