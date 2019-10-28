package com.heystyles.seguridad.api.controller;

import com.heystyles.common.response.Responses;
import com.heystyles.seguridad.api.service.PermisoService;
import domain.PermisoAuth0;
import dto.PermisosResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping(value = "/Permisos")
@RestController
@Api(value = "Controller de Permisos",
        description = "Controller para el manejo de los Permisos en HeyStyles.")
public class PermisoController {

    @Autowired
    private PermisoService permisoService;

    @ApiOperation(value = "Permite Listar todas los Permisos de la base de datos")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Permisos Encontrados."),
            @ApiResponse(code = 404, message = "Permisos no encontrados.")
    })
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PermisosResponse> getPersonas() {
        List<PermisoAuth0> personas = permisoService.findAll();
        return Responses.responseEntity(new PermisosResponse(personas));
    }

}
