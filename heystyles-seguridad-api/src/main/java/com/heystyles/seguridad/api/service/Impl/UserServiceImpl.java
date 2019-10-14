package com.heystyles.seguridad.api.service.Impl;

import com.auth0.client.mgmt.ManagementAPI;
import com.auth0.exception.Auth0Exception;
import com.auth0.json.mgmt.users.User;
import com.heystyles.common.service.ConverterService;
import com.heystyles.seguridad.api.auth0.AuthAPIContext;
import com.heystyles.seguridad.api.exception.Auth0RuntimeException;
import com.heystyles.seguridad.api.service.UserService;
import domain.UserAuth0;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private AuthAPIContext apiContext;

    @Autowired
    private ConverterService converterService;

    @Override
    public String createUserAuth0(UserAuth0 userAuth0) {
        User newUser = null;
        try {
            newUser = converterService.convertTo(userAuth0, User.class);
            ManagementAPI managementAPI = apiContext.newManagementAPI();
            newUser = managementAPI.users().create(newUser).execute();
            return newUser.getId();
        }
        catch (Auth0Exception e) {
            throw new Auth0RuntimeException(e);
        }
    }
}
