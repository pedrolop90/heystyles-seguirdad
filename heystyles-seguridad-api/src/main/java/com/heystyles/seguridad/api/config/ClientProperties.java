package com.heystyles.seguridad.api.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

@Component
@Validated
@ConfigurationProperties("client")
public class ClientProperties {

    private String microSeguridad;

    private int connectionTimeout;

    private int readTimeout;

    public String getMicroSeguridad() {
        return microSeguridad;
    }

    public void setMicroSeguridad(String microSeguridad) {
        this.microSeguridad = microSeguridad;
    }

    public int getConnectionTimeout() {
        return connectionTimeout;
    }

    public void setConnectionTimeout(int connectionTimeout) {
        this.connectionTimeout = connectionTimeout;
    }

    public int getReadTimeout() {
        return readTimeout;
    }

    public void setReadTimeout(int readTimeout) {
        this.readTimeout = readTimeout;
    }


}
