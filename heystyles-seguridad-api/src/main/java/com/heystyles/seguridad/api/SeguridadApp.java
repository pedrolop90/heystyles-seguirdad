package com.heystyles.seguridad.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = {
        "com.heystyles.seguridad.api.async",
        "com.heystyles.seguridad.api.config",
        "com.heystyles.seguridad.api.controller",
        "com.heystyles.seguridad.api.converter",
        "com.heystyles.seguridad.api.dao",
        "com.heystyles.seguridad.api.entity",
        "com.heystyles.seguridad.api.service",
        "com.heystyles.seguridad.api.exception",
        "com.heystyles.seguridad.api.http",
        "com.heystyles.seguridad.api.message",
        "com.heystyles.seguridad.api.service",
        "com.heystyles.seguridad.api.validator",
})
@SpringBootApplication
public class SeguridadApp extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(SeguridadApp.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(SeguridadApp.class, args);
    }

}
