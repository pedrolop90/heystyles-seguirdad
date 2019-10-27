package com.heystyles.seguridad.api.converter;

import com.heystyles.seguridad.api.auth0.RolAuth0Create;
import com.heystyles.seguridad.api.config.Auth0Properties;
import dto.RequestRolAuth0;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class RequestRolAuth0ToRolAuth0CreateConvert implements Converter<RequestRolAuth0, RolAuth0Create> {

    @Autowired
    private Auth0Properties auth0Properties;

    @Override
    public RolAuth0Create convert(RequestRolAuth0 requestRolAuth0) {
        RolAuth0Create rolAuth0Create = new RolAuth0Create();
        rolAuth0Create.setName(requestRolAuth0.getName());
        rolAuth0Create.setDescription(requestRolAuth0.getDescription());
        rolAuth0Create.setApplicationType("client");
        rolAuth0Create.setApplicationId(auth0Properties.getClientId());
        return rolAuth0Create;
    }
}
