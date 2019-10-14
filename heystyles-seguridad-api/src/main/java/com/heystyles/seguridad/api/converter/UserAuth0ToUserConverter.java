package com.heystyles.seguridad.api.converter;

import domain.UserAuth0;
import com.auth0.json.mgmt.users.User;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class UserAuth0ToUserConverter implements Converter<UserAuth0, User> {

    @Override
    public User convert(UserAuth0 source) {
        User user = new User();
        user.setUsername(source.getUserName());
        user.setEmail(source.getEmail());
        user.setEmailVerified(false);
        user.setPassword(source.getPassword());
        user.setGivenName(source.getNombre());
        user.setFamilyName(source.getApellido());
        user.setName(source.getNombre() + " " + source.getApellido());
        user.setConnection("Username-Password-Authentication");
        return user;
    }
}
