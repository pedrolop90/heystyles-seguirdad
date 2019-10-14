package com.heystyles.seguridad.cliente.impl;

import com.heystyles.common.types.StringResponse;
import com.heystyles.seguridad.cliente.UserCliente;
import domain.UserAuth0;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

public class UserClienteImpl implements UserCliente {

    private final RestTemplate client;

    private final String urlBase;

    private interface SegmentPaths {
        String USER = "users";
    }

    public UserClienteImpl(String urlBase, RestTemplate client) {
        this.client = client;
        this.urlBase = urlBase;
    }

    @Override
    public String createUser(UserAuth0 usuario) {
        UriComponentsBuilder urlBuilder = getSecurityUri();
        return client.postForEntity(urlBuilder.toUriString(), usuario, StringResponse.class).getBody().getData();
    }

    private UriComponentsBuilder getSecurityUri() {
        return UriComponentsBuilder.fromHttpUrl(urlBase).pathSegment(SegmentPaths.USER);
    }

}
