package com.heystyles.seguridad.cliente.impl;

import com.heystyles.common.types.StringResponse;
import com.heystyles.seguridad.cliente.RolClient;
import dto.RequestRolAuth0;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

public class RolClientImpl implements RolClient {

    private final RestTemplate client;

    private final String urlBase;

    private interface SegmentPaths {
        String ROLE = "roles";
    }

    public RolClientImpl(String urlBase, RestTemplate client) {
        this.client = client;
        this.urlBase = urlBase;
    }


    @Override
    public String create(RequestRolAuth0 requestRolAuth0) {
        UriComponentsBuilder urlBuilder = getSecurityUri();
        return client.postForEntity(urlBuilder.toUriString(),
                requestRolAuth0, StringResponse.class).getBody().getData();
    }

    @Override
    public void update(String rolId, RequestRolAuth0 requestRolAuth0) {
        UriComponentsBuilder urlBuilder = getSecurityUri().pathSegment(rolId);
        client.put(urlBuilder.toUriString(), requestRolAuth0);
    }

    @Override
    public void delete(String rolId) {
        UriComponentsBuilder urlBuilder = getSecurityUri().pathSegment(rolId);
        client.delete(urlBuilder.toUriString());
    }

    private UriComponentsBuilder getSecurityUri() {
        return UriComponentsBuilder.fromHttpUrl(urlBase)
                .pathSegment(SegmentPaths.ROLE);
    }
}