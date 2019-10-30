package com.heystyles.seguridad.cliente.impl;

import com.heystyles.common.types.IdResponse;
import com.heystyles.seguridad.cliente.RolClient;
import domain.PermisoAuth0Extended;
import domain.RolAuth0;
import dto.PermisosExtendedResponse;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

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
    public Long create(RolAuth0 rolAuth0) {
        UriComponentsBuilder urlBuilder = getSecurityUri();
        return client.postForEntity(urlBuilder.toUriString(),
                                    rolAuth0, IdResponse.class).getBody().getData();
    }

    @Override
    public void update(Long rolId, RolAuth0 rolAuth0) {
        UriComponentsBuilder urlBuilder = getSecurityUri().pathSegment(String.valueOf(rolId));
        client.put(urlBuilder.toUriString(), rolAuth0);
    }

    @Override
    public void delete(Long rolId) {
        UriComponentsBuilder urlBuilder = getSecurityUri().pathSegment(String.valueOf(rolId));
        client.delete(urlBuilder.toUriString());
    }

    @Override
    public List<PermisoAuth0Extended> getPermisos(Long rolId) {
        UriComponentsBuilder urlBuilder = getSecurityUri()
                .pathSegment(String.valueOf(rolId))
                .pathSegment("permisos");
        return client.getForEntity(urlBuilder.toUriString(), PermisosExtendedResponse.class).getBody().getData();
    }

    private UriComponentsBuilder getSecurityUri() {
        return UriComponentsBuilder.fromHttpUrl(urlBase)
                .pathSegment(SegmentPaths.ROLE);
    }
}
