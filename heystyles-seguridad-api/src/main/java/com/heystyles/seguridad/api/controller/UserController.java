package com.heystyles.seguridad.api.controller;

import com.heystyles.common.response.Responses;
import com.heystyles.common.types.StringResponse;
import com.heystyles.seguridad.api.service.UserService;
import domain.UserAuth0;
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

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RequestMapping(value = "/users")
@RestController
@Api(value = "Auth0 User Controller",
        description = "Controller para el manejo de usuarios de heystyles en auth0.")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    @ApiOperation(value = "Crea un usuario  en Auth0")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK")
    })
    public ResponseEntity<StringResponse> insert(
            @NotNull @Valid @RequestBody UserAuth0 usuarioAuth0) {
        String idUser = userService.createUserAuth0(usuarioAuth0);
        return Responses.responseEntity(new StringResponse(idUser));
    }

}
