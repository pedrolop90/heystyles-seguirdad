package com.heystyles.seguridad.api.auth0;

import com.heystyles.common.response.ClientResponseErrorHandler;
import com.heystyles.seguridad.api.util.UtilDecode;
import domain.CreateGroupAuth0;
import domain.GroupAuth0;
import domain.RolAuth0;
import dto.RolAuth0Extended;
import dto.RolesAuth0Response;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;

public class AuthorizationAPI {

    private String authorizationApiUrl;

    private String token;

    private interface AuthSegmentPaths {
        String GROUPS = "groups";
        String MEMBERS = "members";
        String USERS = "users";
        String ROLES = "roles";
        String PERMISSIONS = "permissions";
    }

    public AuthorizationAPI(String authorizationApiUrl, String token) {
        this.authorizationApiUrl = authorizationApiUrl;
        this.token = token;
    }

    public GroupAuth0 createGroup(CreateGroupAuth0 groupAuth0) {
        try {
            UriComponentsBuilder urlBuilder = UriComponentsBuilder
                    .fromHttpUrl(authorizationApiUrl)
                    .pathSegment(AuthSegmentPaths.GROUPS);
            return this.buildRestTemplate().postForEntity(urlBuilder.toUriString(),
                    groupAuth0, GroupAuth0.class).getBody();
        }
        catch (Exception e) {
            return null;
        }
    }

    public void updateGroup(String groupId, CreateGroupAuth0 groupAuth0) {
        UriComponentsBuilder urlBuilder = UriComponentsBuilder
                .fromHttpUrl(authorizationApiUrl)
                .pathSegment(AuthSegmentPaths.GROUPS)
                .pathSegment(groupId);
        this.buildRestTemplate().put(urlBuilder.toUriString(), groupAuth0);
    }

    public void addUserToGroup(String userAuth0Id, String auth0GroupId) {
        UriComponentsBuilder urlBuilder = this.getGroupUriBuilder(auth0GroupId)
                .pathSegment(AuthSegmentPaths.MEMBERS);
        List<String> data = new ArrayList<>();
        data.add(userAuth0Id);
        String url = UtilDecode.decode(urlBuilder.toUriString());
        this.buildRestTemplate().patchForObject(url, data, Object.class);
    }

    public void deleteUserToGroup(String userAuth0Id, String auth0GroupId) {
        UriComponentsBuilder urlBuilder = this.getGroupUriBuilder(userAuth0Id)
                .pathSegment(AuthSegmentPaths.MEMBERS);
        List<String> data = new ArrayList<>();
        data.add(auth0GroupId);
        String url = UtilDecode.decode(urlBuilder.toUriString());
        this.buildRestTemplate().exchange(url, HttpMethod.DELETE,
                new HttpEntity<>(data), Object.class);
    }


    public void assignRoleToUser(String userAuth0Id, String auth0RoleId) {
        UriComponentsBuilder urlBuilder = this.getUserUriBuilder(userAuth0Id)
                .pathSegment(AuthSegmentPaths.ROLES);
        List<String> rolesData = new ArrayList<>();
        rolesData.add(auth0RoleId);
        String url = UtilDecode.decode(urlBuilder.toUriString());
        this.buildRestTemplate().patchForObject(url, rolesData, Object.class);
    }


    public void deleteRoleToUser(String userAuth0Id, String auth0RoleId) {
        UriComponentsBuilder urlBuilder = this.getUserUriBuilder(userAuth0Id)
                .pathSegment(AuthSegmentPaths.ROLES);
        List<String> rolesData = new ArrayList<>();
        rolesData.add(auth0RoleId);
        String url = UtilDecode.decode(urlBuilder.toUriString());
        this.buildRestTemplate().exchange(url, HttpMethod.DELETE,
                new HttpEntity<>(rolesData), Object.class);
    }

    public List<RolAuth0> findRolByUserId(String userId) {
        UriComponentsBuilder urlBuilder = this.getUserUriBuilder(userId)
                .pathSegment(AuthSegmentPaths.ROLES);
        String url = UtilDecode.decode(urlBuilder.toUriString());
        ResponseEntity<List> response = this.buildRestTemplate().getForEntity(url, List.class);
        return response.getBody();
    }


    public RolAuth0 createRol(RolAuth0Create rolAuth0Create) {
        UriComponentsBuilder urlBuilder = UriComponentsBuilder
                .fromHttpUrl(authorizationApiUrl)
                .pathSegment(AuthSegmentPaths.ROLES);
        RolAuth0 roleResponse =
                this.buildRestTemplate().postForEntity(
                        urlBuilder.toUriString(),
                        rolAuth0Create, RolAuth0.class).getBody();
        return roleResponse;
    }

    public void updateRol(String rolId, RolAuth0Create rolAuth0Create) {
        UriComponentsBuilder urlBuilder = UriComponentsBuilder
                .fromHttpUrl(authorizationApiUrl)
                .pathSegment(AuthSegmentPaths.ROLES)
                .pathSegment(rolId);
        String url = UtilDecode.decode(urlBuilder.toUriString());
        this.buildRestTemplate().put(url, rolAuth0Create);
    }

    public void deleteRol(String rolId) {
        UriComponentsBuilder urlBuilder = UriComponentsBuilder
                .fromHttpUrl(authorizationApiUrl)
                .pathSegment(AuthSegmentPaths.ROLES)
                .pathSegment(rolId);
        String url = UtilDecode.decode(urlBuilder.toUriString());
        this.buildRestTemplate().delete(url);
    }

    public List<RolAuth0Extended> getRoles() {
        UriComponentsBuilder urlBuilder = UriComponentsBuilder
                .fromHttpUrl(authorizationApiUrl)
                .pathSegment(AuthSegmentPaths.ROLES);
        RolesAuth0Response rolesAuth0Response =
                this.buildRestTemplate().getForEntity(urlBuilder.toUriString(),
                        RolesAuth0Response.class).getBody();
        return rolesAuth0Response.getRoles();
    }

    public List<PermissionAuth0> getPermissions() {
        UriComponentsBuilder urlBuilder = UriComponentsBuilder
                .fromHttpUrl(authorizationApiUrl)
                .pathSegment(AuthSegmentPaths.PERMISSIONS);
        PermissionsAuth0Response rolesAuth0Response =
                this.buildRestTemplate().getForEntity(urlBuilder.toUriString(),
                        PermissionsAuth0Response.class).getBody();
        return rolesAuth0Response.getPermissions();
    }

    private UriComponentsBuilder getGroupUriBuilder(String auht0GroupId) {
        return UriComponentsBuilder
                .fromHttpUrl(authorizationApiUrl)
                .pathSegment(AuthSegmentPaths.GROUPS)
                .pathSegment(auht0GroupId);
    }

    private UriComponentsBuilder getUserUriBuilder(String userAuth0Id) {
        return UriComponentsBuilder
                .fromHttpUrl(authorizationApiUrl)
                .pathSegment(AuthSegmentPaths.USERS)
                .pathSegment(userAuth0Id);
    }

    private RestTemplate buildRestTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        List<ClientHttpRequestInterceptor> interceptors = new ArrayList<>();
        interceptors.add(new HeaderRequestInterceptor("Authorization", "Bearer ".concat(this.token)));
        restTemplate.setInterceptors(interceptors);
        restTemplate.setErrorHandler(new ClientResponseErrorHandler());
        //restTemplate.setRequestFactory(new HttpComponentsClientHttpRequestFactory());
        return restTemplate;
    }

}


