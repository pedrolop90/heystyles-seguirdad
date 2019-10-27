package com.heystyles.seguridad.api.controller;

import com.heystyles.common.response.Responses;
import com.heystyles.common.types.BaseResponse;
import com.heystyles.common.types.StringResponse;
import com.heystyles.seguridad.api.service.RoleService;
import com.heystyles.seguridad.api.util.UtilDecode;
import domain.RolAuth0;
import dto.RequestRolAuth0;
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

@RestController
@RequestMapping(path = "/roles")
@Api(value = "Role Controller",
        description = "Controller para el manejo de roles en el aplicativo Jamestown.")
public class RolController {

    @Autowired
    private RoleService roleService;

    @ApiOperation(value = "Permite insertar un nuevo rol.")
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Rol creado Exitosamente.")
    })
    public ResponseEntity<StringResponse> insert(
            @NotNull @Valid @RequestBody RequestRolAuth0 requestRolAuth0) {
        RolAuth0 response = roleService.insert(requestRolAuth0);
        return Responses.responseEntity(new StringResponse(response.getId()));
    }

    @ApiOperation(value = "Permite actualizar un rol.")
    @PutMapping(path = "/{rolId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Rol Actualizado Exitosamente.")
    })
    public BaseResponse update(@NotBlank @PathVariable String rolId,
                               @NotNull @Valid @RequestBody RequestRolAuth0 request) {
        rolId = UtilDecode.decode(rolId);
        roleService.update(rolId, request);
        return Responses.success("El rol se Actualizo de forma correcta.");
    }

    @ApiOperation(value = "Permite Eliminar un rol.")
    @DeleteMapping(path = "/{rolId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Rol Eliminado Exitosamente.")
    })
    public BaseResponse delete(@NotBlank @PathVariable String rolId) {
        rolId = UtilDecode.decode(rolId);
        roleService.delete(rolId);
        return Responses.success("Rol Eliminado de forma Exitosa.");
    }

}
