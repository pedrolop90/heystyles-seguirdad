package com.heystyles.seguridad.api.controller;

import com.heystyles.common.response.Responses;
import com.heystyles.common.types.BaseResponse;
import com.heystyles.common.types.StringResponse;
import com.heystyles.seguridad.api.service.RoleService;
import com.heystyles.seguridad.api.service.UserService;
import com.heystyles.seguridad.api.util.UtilDecode;
import domain.EstadoUser;
import domain.UserAuth0;
import domain.UsuarioAuth0;
import dto.RemoveRolToUserResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.QueryParam;

@RequestMapping(value = "/users")
@RestController
@Api(value = "Auth0 User Controller",
        description = "Controller para el manejo de usuarios de heystyles en auth0.")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

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


    @DeleteMapping("/{idUsuario}")
    @ApiOperation(value = "Elimina un usuario de Auth0 por su Id")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK")
    })
    public ResponseEntity<BaseResponse> deleteUsuario(@PathVariable String idUsuario) {
        userService.deleteAuth0User(idUsuario);
        return Responses.successEntity("Usuario eliminado correctamento de Auht0.");
    }

    @ApiOperation(value = "Permite asignar un rol a un usuario.")
    @PostMapping(path = "/{userId}/rol", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Rol Asignado Exitosamente.")
    })
    public BaseResponse assignRoleToUser(
            @NotBlank @PathVariable String userId,
            @NotBlank @QueryParam("rolNewId") String rolNewId) {
        userId = UtilDecode.decode(userId);
        roleService.assignRoleToUser(rolNewId, userId);
        return Responses.success("Rol Asignado Exitosamente");
    }

    @PutMapping("/{userId}/rol")
    @ApiOperation(value = "Actualiza el rol de un usuario.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK")
    })
    public ResponseEntity<BaseResponse> updateRolUser(
            @NotBlank @PathVariable String userId,
            @NotBlank @QueryParam("rolLastId") String rolLastId,
            @NotBlank @QueryParam("rolNewId") String rolNewId) {
        userId = UtilDecode.decode(userId);
        roleService.updateRolToUser(userId, rolLastId, rolNewId);
        return Responses.successEntity("Usuario actualizo su rol de forma exitosa.");
    }

    @DeleteMapping("/{userId}/rol")
    @ApiOperation(value = "Remueve el rol de un usuario.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK")
    })
    public ResponseEntity<RemoveRolToUserResponse> removeRolUser(
            @NotBlank @PathVariable String userId,
            @NotBlank @QueryParam("rolLastId") String rolLastId) {
        userId = UtilDecode.decode(userId);
        EstadoUser estadoUser = roleService.removeRolToUser(userId, rolLastId);
        return Responses.responseEntity(new RemoveRolToUserResponse(estadoUser));
    }

    @PutMapping("/{idUsuario}")
    @ApiOperation(value = "Actualiza un usuario de Auth0 por su Id")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK")
    })
    public ResponseEntity<BaseResponse> updateUsuario(
            @PathVariable String idUsuario, @RequestBody UsuarioAuth0 usuarioAuth0) {
        idUsuario = UtilDecode.decode(idUsuario);
        userService.updateUser(idUsuario, usuarioAuth0);
        return Responses.successEntity("Usuario actualizado correctamente en auth0.");
    }

}
