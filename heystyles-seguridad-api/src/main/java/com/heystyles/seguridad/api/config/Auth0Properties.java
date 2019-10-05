package com.heystyles.seguridad.api.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.List;

@ConfigurationProperties(prefix = "auth0")
@Component
@PropertySource("classpath:application.properties")
public class Auth0Properties {

    private String clientSecret;
    private String domain;
    private String clientId;
    private String audience;
    private String scope;
    private String authorizationApi;
    private String tokenAudience;
    private String managementAudience;
    private String urlApi;
    private List<String> permissionsBase;

    public String getClientSecret() {
        return clientSecret;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getAudience() {
        return audience;
    }

    public void setAudience(String audience) {
        this.audience = audience;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getAuthorizationApi() {
        return authorizationApi;
    }

    public void setAuthorizationApi(String authorizationApi) {
        this.authorizationApi = authorizationApi;
    }

    public String getTokenAudience() {
        return tokenAudience;
    }

    public void setTokenAudience(String tokenAudience) {
        this.tokenAudience = tokenAudience;
    }

    public String getManagementAudience() {
        return managementAudience;
    }

    public void setManagementAudience(String managementAudience) {
        this.managementAudience = managementAudience;
    }

    public String getUrlApi() {
        return urlApi;
    }

    public void setUrlApi(String urlApi) {
        this.urlApi = urlApi;
    }

    public List<String> getPermissionsBase() {
        return permissionsBase;
    }

    public void setPermissionsBase(List<String> permissionsBase) {
        this.permissionsBase = permissionsBase;
    }
}
