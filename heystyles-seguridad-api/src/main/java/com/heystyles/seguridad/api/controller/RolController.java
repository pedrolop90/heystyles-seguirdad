package com.heystyles.seguridad.api.controller;

import com.heystyles.common.response.Responses;
import com.heystyles.common.types.BaseResponse;
import com.heystyles.common.types.IdResponse;
import com.heystyles.seguridad.api.service.RolPermisoService;
import com.heystyles.seguridad.api.service.RoleService;
import domain.PermisoAuth0;
import domain.RolAuth0;
import dto.PermisosResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping(path = "/roles")
@Api(value = "Role Controller", description = "Controller para el manejo de roles en el aplicativo Jamestown.")
public class RolController {

    @Autowired
    private RoleService roleService;

    @Autowired
    private RolPermisoService rolPermisoService;

    @ApiOperation(value = "Permite insertar un nuevo rol.")
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Rol creado Exitosamente.")
    })
    public ResponseEntity<IdResponse> insert(
            @NotNull @Valid @RequestBody RolAuth0 requestRolAuth0) {
        RolAuth0 response = roleService.insert(requestRolAuth0);
        return Responses.responseEntity(new IdResponse(response.getId()));
    }

    @ApiOperation(value = "Permite actualizar un rol.")
    @PutMapping(path = "/{rolId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Rol Actualizado Exitosamente.")
    })
    public BaseResponse update(@NotBlank @PathVariable Long rolId,
                               @NotNull @Valid @RequestBody RolAuth0 request) {
        roleService.update(rolId, request);
        return Responses.success("El rol se Actualizo de forma correcta.");
    }

    @ApiOperation(value = "Permite Eliminar un rol.")
    @DeleteMapping(path = "/{rolId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Rol Eliminado Exitosamente.")
    })
    public BaseResponse delete(@NotBlank @PathVariable Long rolId) {
        roleService.delete(rolId);
        return Responses.success("Rol Eliminado de forma Exitosa.");
    }


    @ApiOperation(value = "Permite listar los permisos de un rol.")
    @GetMapping(path = "/{rolId}/permisos", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Rol Eliminado Exitosamente.")
    })
    public ResponseEntity<PermisosResponse> getPermisos(@NotBlank @PathVariable Long rolId) {
        List<PermisoAuth0> data = rolPermisoService.findByRolId(rolId);
        return Responses.responseEntity(new PermisosResponse(data));
    }
}
