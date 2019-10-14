package com.heystyles.seguridad.api.controller;

import com.heystyles.seguridad.api.service.AuthService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(value = "/auth")
@RestController
@Api(value = "Auth0 Controller", description = "Controller para el manejo de "
        + " la seguridad en HeyStyles.")
public class Auth0Controller {

    @Autowired
    private AuthService service;


}
