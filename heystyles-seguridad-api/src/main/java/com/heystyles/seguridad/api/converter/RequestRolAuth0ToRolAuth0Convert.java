package com.heystyles.seguridad.api.converter;

import domain.RolAuth0;
import dto.RequestRolAuth0;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class RequestRolAuth0ToRolAuth0Convert implements Converter<RequestRolAuth0, RolAuth0> {

    @Override
    public RolAuth0 convert(RequestRolAuth0 requestRolAuth0) {
        RolAuth0 rolAuth0 = new RolAuth0();
        rolAuth0.setNombre(requestRolAuth0.getName());
        rolAuth0.setDescripcion(requestRolAuth0.getDescription());
        return rolAuth0;
    }
}
