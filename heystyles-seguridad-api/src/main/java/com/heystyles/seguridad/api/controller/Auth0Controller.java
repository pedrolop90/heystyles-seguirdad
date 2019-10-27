package com.heystyles.seguridad.api.controller;

import com.heystyles.common.response.Responses;
import com.heystyles.common.types.BaseResponse;
import com.heystyles.seguridad.api.service.AuthService;
import domain.Login;
import domain.SessionToken;
import dto.SessionTokenResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(value = "/auth")
@RestController
@Api(value = "Auth0 Controller", description = "Controller para el manejo de "
        + " la seguridad en HeyStyles.")
public class Auth0Controller {

    @Autowired
    private AuthService service;

    @PostMapping("/login")
    @ApiOperation(value = "Obtiene un token basado en el usuario y la contraseña")
    @ApiResponses({
            @ApiResponse(code = 200, message = "SessionToken"),
            @ApiResponse(code = 400, message = "Usuario o contraseña incorrectos")

    })
    public ResponseEntity<BaseResponse> login(@RequestBody Login login) {
        SessionToken sessionToken = service.login(login);
        //sessionTokenServiceProcessor.save(sessionToken);
        return Responses.responseEntity(new SessionTokenResponse(sessionToken));
    }
}
