package com.heystyles.seguridad.cliente.impl;

import com.heystyles.common.types.BaseResponse;
import com.heystyles.common.types.IdResponse;
import com.heystyles.seguridad.cliente.UserCliente;
import domain.EstadoUser;
import domain.UserAuth0;
import dto.RemoveRolToUserResponse;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
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
    public Long createUser(UserAuth0 usuario) {
        UriComponentsBuilder urlBuilder = getSecurityUri();
        return client.postForEntity(urlBuilder.toUriString(), usuario, IdResponse.class).getBody().getData();
    }

    @Override
    public void updateUser(Long idUsuario, UserAuth0 usuario) {
        UriComponentsBuilder urlBuilder = getSecurityUri().pathSegment(String.valueOf(idUsuario));
        client.put(urlBuilder.toUriString(), usuario);
    }


    @Override
    public void assignRolToUser(Long userId, Long rolId) {
        UriComponentsBuilder urlBuilder = getSecurityUri()
                .pathSegment(String.valueOf(userId))
                .pathSegment("rol")
                .queryParam("rolNewId", rolId);
        client.postForEntity(urlBuilder.toUriString(), null, BaseResponse.class);
    }

    @Override
    public void updateRolUser(Long userId, Long rolLastId, Long rolNewId) {
        UriComponentsBuilder urlBuilder = getSecurityUri()
                .pathSegment(String.valueOf(userId))
                .pathSegment("rol")
                .queryParam("rolLastId", rolLastId)
                .queryParam("rolNewId", rolNewId);
        client.put(urlBuilder.toUriString(), null);
    }


    @Override
    public EstadoUser removeRolToUser(Long userId, Long rolId) {
        UriComponentsBuilder urlBuilder = getSecurityUri()
                .pathSegment(String.valueOf(userId))
                .pathSegment("rol")
                .queryParam("rolLastId", rolId);
        ResponseEntity<RemoveRolToUserResponse> response = client.exchange(urlBuilder.toUriString(),
                                                                           HttpMethod.DELETE, null, RemoveRolToUserResponse.class);
        return response.getBody().getData();
    }

    private UriComponentsBuilder getSecurityUri() {
        return UriComponentsBuilder.fromHttpUrl(urlBase).pathSegment(SegmentPaths.USER);
    }

}
