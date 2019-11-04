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
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(value = "/auth")
@RestController
@Api(value = "Auth0 Controller", description = "Controller para el manejo de la seguridad en HeyStyles.")
@Validated
public class Auth0Controller {

    @Autowired
    private AuthService service;

    @PostMapping("/login")
    @ApiOperation(value = "Obten el menu del usuario, para desplegar...")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Menu"),
            @ApiResponse(code = 400, message = "Email o contraseña incorrectos")

    })
    public ResponseEntity<BaseResponse> login(@RequestBody Login login) {
        SessionToken sessionToken = service.login(login);
        return Responses.responseEntity(new SessionTokenResponse(sessionToken));
    }

    @PostMapping("/resetPassword")
    @ApiOperation(value = "restaura una contraseña aleatorio y la envia por gmail, para restaurarla...")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Menu"),
            @ApiResponse(code = 400, message = "Email incorrectos..")

    })
    public BaseResponse resetPassword(@NotBlank @RequestParam String correo) {
        service.resetPassword(correo);
        return Responses.success("contraseña enviada");
    }
}
